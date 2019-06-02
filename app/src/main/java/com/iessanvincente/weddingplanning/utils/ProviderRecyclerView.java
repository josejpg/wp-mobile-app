package com.iessanvincente.weddingplanning.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iessanvincente.weddingplanning.R;
import com.iessanvincente.weddingplanning.domain.ProviderDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProviderRecyclerView extends RecyclerView.Adapter<ProviderRecyclerView.ViewHolder> {
	private List<ProviderDto> providerDtoSet;
	private LayoutInflater mInflater;
	private ItemClickListener mClickListener;

	// Data is passed into the constructor
	public ProviderRecyclerView( Context context, Set<ProviderDto> providerDtoSet ) {
		this.mInflater = LayoutInflater.from( context );
		this.providerDtoSet = new ArrayList<>( providerDtoSet );
	}

	// Inflates the row layout from xml when needed
	@Override
	@NonNull
	public ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
		View view = mInflater.inflate( R.layout.recyclerview_provider_item, parent, false );
		return new ViewHolder( view );
	}

	// Binds the data to the view and textview in each row
	@Override
	public void onBindViewHolder( @NonNull ViewHolder holder, int position ) {
		ProviderDto providerDto = providerDtoSet.get( position );
		holder.clientView.setText( ( providerDto.getName() != null ) ? providerDto.getName() : providerDto.getCif() );
	}

	// Total number of rows
	@Override
	public int getItemCount( ) {
		return providerDtoSet.size();
	}

	// convenience method for getting data at click position
	public ProviderDto getItem( int id ) {
		return providerDtoSet.get( id );
	}

	// allows clicks events to be caught
	public void setClickListener( ItemClickListener itemClickListener ) {
		this.mClickListener = itemClickListener;
	}

	// parent activity will implement this method to respond to click events
	public interface ItemClickListener {
		void onItemClick( View view, int position );
	}

	// Stores and recycles views as they are scrolled off screen
	public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		TextView clientView;
		ImageView deleteButtonView;

		ViewHolder( View itemView ) {
			super( itemView );
			clientView = itemView.findViewById( R.id.clientTextView );
			deleteButtonView = itemView.findViewById( R.id.btnDelentEventClient );
			deleteButtonView.setOnClickListener( this );
		}

		@Override
		public void onClick( View view ) {
			if ( mClickListener != null ) mClickListener.onItemClick( view, getAdapterPosition() );
		}
	}
}
