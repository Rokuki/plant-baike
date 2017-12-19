package com.treecute.plant.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.treecute.plant.R;
import com.treecute.plant.databinding.ItemCartBinding;
import com.treecute.plant.model.Cart;
import com.treecute.plant.model.Plant;
import com.treecute.plant.util.TAG;
import com.treecute.plant.viewmodel.ItemCartViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by mkind on 2017/12/18 0018.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartAdapterViewHolder> {

    private List<Cart> carts;

    public CartAdapter() {
        this.carts = Collections.emptyList();
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
        notifyDataSetChanged();
    }

    @Override
    public CartAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCartBinding itemCartBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_cart, parent, false);
        return new CartAdapterViewHolder(itemCartBinding);
    }

    @Override
    public void onBindViewHolder(CartAdapterViewHolder holder, int position) {
        Log.d(TAG.TAG, "onBindViewHolder: " + carts.get(position).getPlant().name);
        holder.bindCart(carts.get(position), carts.get(position).getPlant());
    }

    @Override
    public int getItemCount() {
        return carts == null ? 0 : carts.size();
    }

    class CartAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemCartBinding itemCartBinding;

        CartAdapterViewHolder(ItemCartBinding itemCartBinding) {
            super(itemCartBinding.cartItem);
            this.itemCartBinding = itemCartBinding;
        }

        void bindCart(Cart cart, Plant plant) {
            if (itemCartBinding.getItem() == null) {
                itemCartBinding.setItem(new ItemCartViewModel(itemView.getContext(), cart, plant));
            } else {
                itemCartBinding.getItem().setModel(cart, plant);
            }
        }
    }
}
