package com.brandprotect.client.ui.certificate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import com.brandprotect.client.R;
import com.brandprotect.client.common.Utils;
import com.brandprotect.tronlib.Hosts;
import com.brandprotect.tronlib.ServiceBuilder;
import com.brandprotect.tronlib.services.TokenService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CertificateDetailActivity extends AppCompatActivity {
    public static final String CERTIFICATE_INFO = "certificate_info";
    @BindView(R.id.brandTitle)
    TextView brandTitle;
    @BindView(R.id.productName)
    TextView productNameTv;
    @BindView(R.id.description)
    TextView descriptionTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificate_detail);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Certificate details");
        String stringExtra = getIntent().getStringExtra(CERTIFICATE_INFO);
        Utils.ParsedToken parsedToken = Utils.parseTokenName(stringExtra);

        brandTitle.setText(Html.fromHtml("<b>Brand:</b> " + parsedToken.getBrand()));
        productNameTv.setText(Html.fromHtml("<b>Name of product:</b> " + parsedToken.getName()));
        descriptionTv.setText(Html.fromHtml("<b>Count:</b> " + parsedToken.getAccountBalance() + "<br/><br/>" + "<b>Date:</b> " + parsedToken.getDate()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
