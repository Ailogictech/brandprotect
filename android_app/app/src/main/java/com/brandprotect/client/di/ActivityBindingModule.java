package com.brandprotect.client.di;

import com.brandprotect.client.ui.about.AboutActivity;
import com.brandprotect.client.ui.accountdetail.AccountDetailActivity;
import com.brandprotect.client.ui.accountdetail.overview.AccountVoteActivity;
import com.brandprotect.client.ui.accountdetail.overview.AccountVoteActivityModule;
import com.brandprotect.client.ui.accountdetail.overview.OverviewModule;
import com.brandprotect.client.ui.accountdetail.representative.RepresentativeModule;
import com.brandprotect.client.ui.accountdetail.transaction.TransactionModule;
import com.brandprotect.client.ui.accountdetail.transfer.TransferModule;
import com.brandprotect.client.ui.address.AddressActivity;
import com.brandprotect.client.ui.address.AddressActivityModule;
import com.brandprotect.client.ui.backupaccount.BackupAccountActivity;
import com.brandprotect.client.ui.backupaccount.BackupAccountActivityModule;
import com.brandprotect.client.ui.blockdetail.BlockDetailActivity;
import com.brandprotect.client.ui.blockdetail.fragment.BlockInfoModule;
import com.brandprotect.client.ui.blockexplorer.BlockExplorerActivity;
import com.brandprotect.client.ui.createwallet.CreateWalletActivity;
import com.brandprotect.client.ui.createwallet.CreateWalletActivityModule;
import com.brandprotect.client.ui.exchange.ExchangeActivity;
import com.brandprotect.client.ui.exchange.ExchangeActivityModule;
import com.brandprotect.client.ui.importkey.ImportPrivateKeyActivity;
import com.brandprotect.client.ui.importkey.ImportPrivateKeyActivityModule;
import com.brandprotect.client.ui.intro.IntroActivity;
import com.brandprotect.client.ui.intro.IntroActivityModule;
import com.brandprotect.client.ui.login.LoginActivity;
import com.brandprotect.client.ui.login.LoginActivityModule;
import com.brandprotect.client.ui.main.MainActivity;
import com.brandprotect.client.ui.main.MainActivityModule;
import com.brandprotect.client.ui.market.MarketActivity;
import com.brandprotect.client.ui.market.MarketActivityModule;
import com.brandprotect.client.ui.more.MoreActivity;
import com.brandprotect.client.ui.more.MoreActivityModule;
import com.brandprotect.client.ui.myaccount.MyAccountActivity;
import com.brandprotect.client.ui.myaccount.MyAccountActivityModule;
import com.brandprotect.client.ui.mytransfer.MyTransferActivityModule;
import com.brandprotect.client.ui.mytransfer.TransferActivity;
import com.brandprotect.client.ui.node.NodeActivity;
import com.brandprotect.client.ui.node.NodeActivityModule;
import com.brandprotect.client.ui.qrscan.QrScanActivity;
import com.brandprotect.client.ui.representative.RepresentativeActivity;
import com.brandprotect.client.ui.representative.RepresentativeActivityModule;
import com.brandprotect.client.ui.requestcoin.RequestCoinActivity;
import com.brandprotect.client.ui.sendtoken.SendTokenActivity;
import com.brandprotect.client.ui.sendtoken.SendTokenActivityModule;
import com.brandprotect.client.ui.token.TokenActivity;
import com.brandprotect.client.ui.token.TokenActivityModule;
import com.brandprotect.client.ui.token.TokenDetailActivity;
import com.brandprotect.client.ui.vote.VoteActivity;
import com.brandprotect.client.ui.vote.VoteActivityModule;

import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;

@Module(includes = AndroidInjectionModule.class)
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = { IntroActivityModule.class })
    abstract IntroActivity bindIntroActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = { LoginActivityModule.class })
    abstract LoginActivity bindLoginActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract AboutActivity bindAboutActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = { AddressActivityModule.class })
    abstract AddressActivity bindAddressActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = { BackupAccountActivityModule.class })
    abstract BackupAccountActivity bindBackupAccountActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = { CreateWalletActivityModule.class })
    abstract CreateWalletActivity bindCreateWalletActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = { ImportPrivateKeyActivityModule.class })
    abstract ImportPrivateKeyActivity bindImportPrivateKeyActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = { MainActivityModule.class })
    abstract MainActivity bindMainActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = { MarketActivityModule.class })
    abstract MarketActivity bindMarketActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = { MoreActivityModule.class })
    abstract MoreActivity bindMoreActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = { MyAccountActivityModule.class })
    abstract MyAccountActivity bindMyAccountActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = { MyTransferActivityModule.class })
    abstract TransferActivity bindTransferActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = { NodeActivityModule.class })
    abstract NodeActivity bindNodeActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = { RepresentativeActivityModule.class })
    abstract RepresentativeActivity bindRepresentativeActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = { SendTokenActivityModule.class })
    abstract SendTokenActivity bindSendTokenActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = { TokenActivityModule.class })
    abstract TokenActivity bindTokenActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = { VoteActivityModule.class })
    abstract VoteActivity bindVoteActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {
            OverviewModule.class,
            TransactionModule.class,
            TransferModule.class,
            RepresentativeModule.class
    })
    abstract AccountDetailActivity bindAccountDetailActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {AccountVoteActivityModule.class})
    abstract AccountVoteActivity bindAccountVoteActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {
            com.brandprotect.client.ui.blockexplorer.overview.OverviewModule.class,
            com.brandprotect.client.ui.blockexplorer.account.AccountModule.class,
            com.brandprotect.client.ui.blockexplorer.block.BlockModule.class,
            com.brandprotect.client.ui.blockexplorer.transaction.TransactionModule.class,
            com.brandprotect.client.ui.blockexplorer.transfer.TransferModule.class
    })
    abstract BlockExplorerActivity bindBlockExplorerActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {
            BlockInfoModule.class,
            TransactionModule.class,
            TransferModule.class
    })
    abstract BlockDetailActivity bindBlockDetailActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract QrScanActivity bindQrScanActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract RequestCoinActivity bindRequestCoinActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {
            com.brandprotect.client.ui.token.overview.OverviewModule.class,
            com.brandprotect.client.ui.token.holder.HolderModule.class,
            com.brandprotect.client.ui.token.transfer.TransferModule.class
    })
    abstract TokenDetailActivity bindTokenDetailActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = { ExchangeActivityModule.class })
    abstract ExchangeActivity exchangeActivity();
}
