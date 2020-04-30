package com.github.formatc00.ui.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T>(
    private val items: MutableList<T> = ArrayList()
) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder<T>>() {
    
    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(items[position], position)
    }
    
    override fun getItemCount() = items.size
    
    fun addItem(item: T?) {
        item?.apply {
            items.add(this)
            notifyItemInserted(items.size)
        }
    }
    
    fun addItemAt(position: Int, item: T?) {
        item?.apply {
            items.add(position, this)
            notifyItemInserted(position)
        }
    }
    
    fun addAll(newItemList: List<T>?) {
        newItemList?.apply {
            items.addAll(this)
            notifyItemRangeInserted(items.size - this.size, this.size)
        }
    }
    
    fun replaceItem(item: T?): Int {
        val position: Int?
        if (item == null) {
            position = -1
        } else {
            position = items.indexOf(item)
            if (position > -1) {
                items[position] = item
                notifyItemChanged(position)
            }
        }
        return position
    }
    
    fun replaceItems(newItemList: List<T>?) {
        if (items.isEmpty()) {
            addAll(newItemList)
        } else {
            items.clear()
            newItemList?.apply { items.addAll(this) }
            notifyDataSetChanged()
        }
    }
    
    fun removeItem(item: T) {
        val position = items.indexOf(item)
        if (position > -1) {
            removeItemAt(position)
        }
    }
    
    fun removeItemAt(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemChanged(position, itemCount - position)
    }
    
    abstract class BaseViewHolder<T>(parent: ViewGroup, @LayoutRes layoutRes: Int) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
        ) {
        
        protected val resources: Resources = parent.resources
        
        var item: T? = null
            private set
            get() = field ?: throw IllegalStateException("Can't retrieve item before bind call")
        
        @CallSuper
        open fun bind(item: T, position: Int) {
            this.item = item
        }
    }
}