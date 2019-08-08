/*
 * Copyright (c) [2016] [ <ether.camp> ]
 * This file is part of the ethereumJ library.
 *
 * The ethereumJ library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The ethereumJ library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the ethereumJ library. If not, see <http://www.gnu.org/licenses/>.
 */

package org.tron.common.crypto;

import android.util.Log;

import org.tron.common.crypto.jce.TronCastleProvider;
import org.tron.core.config.Parameter.CommonConstant;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;

import static java.util.Arrays.copyOfRange;

public class Hash {

  //private static final Logger LOG = LoggerFactory.getLogger(Hash.class);
  private static final Provider CRYPTO_PROVIDER;

  private static final String HASH_256_ALGORITHM_NAME;
  private static final String HASH_512_ALGORITHM_NAME;

  private static final MessageDigest sha256digest;

  static {
    Security.addProvider(TronCastleProvider.getInstance());
    CRYPTO_PROVIDER = Security.getProvider("SC");
    HASH_256_ALGORITHM_NAME = "TRON-KECCAK-256";
    HASH_512_ALGORITHM_NAME = "TRON-KECCAK-512";
    try {
      sha256digest = MessageDigest.getInstance("SHA-256");
    } catch (NoSuchAlgorithmException e) {
      Log.e(Hash.class.getSimpleName(), "Can't initialize HashUtils", e);
      throw new RuntimeException(e); // Can't happen.
    }

  }

  /**
   * @param input - data for hashing
   * @return - sha256 hash of the data
   */
  public static byte[] sha256(byte[] input) {
    return sha256digest.digest(input);
  }

  public static byte[] sha3(byte[] input) {
    MessageDigest digest;
    try {
      digest = MessageDigest.getInstance(HASH_256_ALGORITHM_NAME,
          CRYPTO_PROVIDER);
      digest.update(input);
      return digest.digest();
    } catch (NoSuchAlgorithmException e) {
      Log.e(Hash.class.getSimpleName(), "Can't find such algorithm", e);
      throw new RuntimeException(e);
    }

  }

  public static byte[] sha3(byte[] input1, byte[] input2) {
    MessageDigest digest;
    try {
      digest = MessageDigest.getInstance(HASH_256_ALGORITHM_NAME,
          CRYPTO_PROVIDER);
      digest.update(input1, 0, input1.length);
      digest.update(input2, 0, input2.length);
      return digest.digest();
    } catch (NoSuchAlgorithmException e) {
      Log.e(Hash.class.getSimpleName(), "Can't find such algorithm", e);
      throw new RuntimeException(e);
    }
  }

  /**
   * hashing chunk of the data
   *
   * @param input - data for hash
   * @param start - start of hashing chunk
   * @param length - length of hashing chunk
   * @return - keccak hash of the chunk
   */
  public static byte[] sha3(byte[] input, int start, int length) {
    MessageDigest digest;
    try {
      digest = MessageDigest.getInstance(HASH_256_ALGORITHM_NAME,
          CRYPTO_PROVIDER);
      digest.update(input, start, length);
      return digest.digest();
    } catch (NoSuchAlgorithmException e) {
      Log.e(Hash.class.getSimpleName(), "Can't find such algorithm", e);
      throw new RuntimeException(e);
    }
  }

  public static byte[] sha512(byte[] input) {
    MessageDigest digest;
    try {
      digest = MessageDigest.getInstance(HASH_512_ALGORITHM_NAME,
          CRYPTO_PROVIDER);
      digest.update(input);
      return digest.digest();
    } catch (NoSuchAlgorithmException e) {
      Log.e(Hash.class.getSimpleName(), "Can't find such algorithm", e);
      throw new RuntimeException(e);
    }
  }

  /**
   * Calculates RIGTMOST160(SHA3(input)). This is used in address calculations. *
   *
   * @param input - data
   * @return - add_pre_fix + 20 right bytes of the hash keccak of the data
   */
  public static byte[] sha3omit12(byte[] input) {
    byte[] hash = sha3(input);
    byte[] address = copyOfRange(hash, 11, hash.length);

    address[0] = CommonConstant.getAddressPrefix();
    return address;
  }
}
