/*
 * java-tron is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * java-tron is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.tron.common.utils;

import com.google.protobuf.ByteString;

import org.tron.common.crypto.ECKey;
import org.tron.common.crypto.ECKey.ECDSASignature;
import org.tron.protos.Protocol.Transaction;
import org.tron.protos.Protocol.Transaction.Contract;

import java.security.SignatureException;
import java.util.Arrays;
import java.util.List;

import static org.tron.common.crypto.Hash.sha256;

public class TransactionUtils {

//  private static final Logger logger = LoggerFactory.getLogger("Transfer");
  private final static int RESERVE_BALANCE = 10;

  /**
   * Obtain a data bytes after removing the id and SHA-256(data)
   *
   * @param transaction {@link Transaction} transaction
   * @return byte[] the hash of the transaction's data bytes which have no id
   */
  public static byte[] getHash(Transaction transaction) {
    Transaction.Builder tmp = transaction.toBuilder();
    //tmp.clearId();

    return sha256(tmp.build().toByteArray());
  }

  public static byte[] getOwner(Contract contract) {
    ByteString owner;
    try {
//      switch (contract.getType()) {
//        case AccountCreateContract:
//          owner = contract.getParameter().unpack(org.tron.protos.Contract.AccountCreateContract.class).getOwnerAddress();
//          break;
//        case TransferContract:
//          owner = contract.getParameter().unpack(org.tron.protos.Contract.TransferContract.class).getOwnerAddress();
//          break;
//        case TransferAssetContract:
//          owner = contract.getParameter().unpack(org.tron.protos.Contract.TransferAssetContract.class).getOwnerAddress();
//          break;
//        case VoteAssetContract:
//          owner = contract.getParameter().unpack(org.tron.protos.Contract.VoteAssetContract.class).getOwnerAddress();
//          break;
//        case VoteWitnessContract:
//          owner = contract.getParameter().unpack(org.tron.protos.Contract.VoteWitnessContract.class).getOwnerAddress();
//          break;
//        case WitnessCreateContract:
//          owner = contract.getParameter().unpack(org.tron.protos.Contract.WitnessCreateContract.class).getOwnerAddress();
//          break;
//        case AssetIssueContract:
//          owner = contract.getParameter().unpack(org.tron.protos.Contract.AssetIssueContract.class).getOwnerAddress();
//          break;
//        case ParticipateAssetIssueContract:
//          owner = contract.getParameter().unpack(org.tron.protos.Contract.ParticipateAssetIssueContract.class).getOwnerAddress();
//          break;
//        case DeployContract:
//          owner = contract.getParameter().unpack(org.tron.protos.Contract.AssetIssueContract.class).getOwnerAddress();
//          break;
//        default:
//          return null;
//      }
//      return owner.toByteArray();
      return null;
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  public static String getBase64FromByteString(ByteString sign) {
    byte[] r = sign.substring(0, 32).toByteArray();
    byte[] s = sign.substring(32, 64).toByteArray();
    byte v = sign.byteAt(64);
    if (v < 27) {
      v += 27; //revId -> v
    }
    ECDSASignature signature = ECDSASignature.fromComponents(r, s, v);
    return signature.toBase64();
  }

  /*
   * 1. check hash
   * 2. check double spent
   * 3. check sign
   * 4. check balance
   */
  public static boolean validTransaction(Transaction signedTransaction) {
    assert (signedTransaction.getSignatureCount() ==
            signedTransaction.getRawData().getContractCount());
    List<Transaction.Contract> listContract = signedTransaction.getRawData().getContractList();
    byte[] hash = sha256(signedTransaction.getRawData().toByteArray());
    int count = signedTransaction.getSignatureCount();
    if (count == 0) {
      return false;
    }
    for (int i = 0; i < count; ++i) {
      try {
        Transaction.Contract contract = listContract.get(i);
        byte[] owner = getOwner(contract);
        byte[] address = ECKey
                .signatureToAddress(hash, getBase64FromByteString(signedTransaction.getSignature(i)));
        if (!Arrays.equals(owner, address)) {
          return false;
        }
      } catch (SignatureException e) {
        e.printStackTrace();
        return false;
      }
    }
    return true;
  }

  public static Transaction sign(Transaction transaction, ECKey myKey) {
    ByteString lockSript = ByteString.copyFrom(myKey.getAddress());
    Transaction.Builder transactionBuilderSigned = transaction.toBuilder();

    byte[] hash = sha256(transaction.getRawData().toByteArray());
    List<Contract> listContract = transaction.getRawData().getContractList();
    for (int i = 0; i < listContract.size(); i++) {
      ECDSASignature signature = myKey.sign(hash);
      ByteString bsSign = ByteString.copyFrom(signature.toByteArray());
      transactionBuilderSigned.addSignature(
              bsSign);//Each contract may be signed with a different private key in the future.
    }

    transaction = transactionBuilderSigned.build();
    return transaction;
  }

  public static Transaction setTimestamp(Transaction transaction){
    long currenTime = System.nanoTime();
    Transaction.Builder builder = transaction.toBuilder();
    Transaction.raw.Builder rowBuilder = transaction.getRawData()
        .toBuilder();
    rowBuilder.setTimestamp(currenTime);
    builder.setRawData(rowBuilder.build());
    return builder.build();
  }
}
