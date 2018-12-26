package com.brandprotect.client.ui.certificate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.brandprotect.client.R;

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
        String[] split = stringExtra.split(";");
        if (split.length > 0 && split.length == 3) {
            String brand = split[0];
            String productName = split[1];
            String description = split[2];
            brandTitle.setText(brand);
            productNameTv.setText(productName);
            descriptionTv.setText(description);
        } else {
            brandTitle.setText(stringExtra);
        }
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
