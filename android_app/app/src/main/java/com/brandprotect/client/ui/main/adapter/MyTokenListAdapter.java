package com.brandprotect.client.ui.main.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brandprotect.client.R;
import com.brandprotect.client.common.AdapterDataModel;
import com.brandprotect.client.common.AdapterView;
import com.brandprotect.client.common.Constants;
import com.brandprotect.client.common.Utils;
import com.brandprotect.client.ui.main.dto.Asset;
import com.brandprotect.tronlib.Hosts;
import com.brandprotect.tronlib.ServiceBuilder;
import com.brandprotect.tronlib.services.TokenService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MyTokenListAdapter extends RecyclerView.Adapter<MyTokenListAdapter.MyTokenViewHolder> implements AdapterDataModel<Asset>, AdapterView {

    private List<Asset> mList = new ArrayList<>();
    private OnItemClick listener;
    private TokenService service;

    public MyTokenListAdapter(OnItemClick listener) {
        service = ServiceBuilder.createService(TokenService.class, Hosts.TRONSCAN_API);
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyTokenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_brand_token, null);
//        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
//                RecyclerView.LayoutParams.WRAP_CONTENT));
        return new MyTokenViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTokenViewHolder holder, int position) {
        Asset item = mList.get(position);

        holder.tokenNameText.setText("Cert. № " + item.getSampleName());
        holder.tokenNameText.setTag(item.getName());
        holder.tokenAmountText.setText(Constants.brandBalanceFormat.format(item.getBalance()));

        service.getTokenDetailById(item.getSampleName())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((tokens, throwable) -> {
            if (throwable == null && tokens != null) {
                holder.tokenNameText.setText(Utils.parseTokenName(tokens.getData().get(0).getName()).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void add(Asset model) {
        mList.add(model);
        notifyItemInserted(getItemCount() - 1);
    }

    @Override
    public void addAll(List<Asset> list) {
        mList.addAll(list);
        notifyItemInserted(getItemCount() - 1);
    }

    @Override
    public void remove(int position) {
        mList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public Asset getModel(int position) {
        return mList.get(position);
    }

    @Override
    public int getSize() {
        return mList.size();
    }

    @Override
    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }

    public class MyTokenViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.token_name_text)
        TextView tokenNameText;

        @BindView(R.id.token_amount_text)
        TextView tokenAmountText;

        public MyTokenViewHolder(View itemView, OnItemClick listener) {
            super(itemView);
            itemView.setOnClickListener(v -> {
                Object tag = tokenNameText.getTag();
                if (tag instanceof String && !TextUtils.isEmpty((CharSequence) tag)) {
                    listener.onCertificateSelected((String) tag);
                }
            });
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClick {
        void onCertificateSelected(String certificateInfo);
    }
}
