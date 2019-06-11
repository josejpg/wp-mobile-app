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
import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * @author Jose J. Pardines
 */
public class ProviderRecyclerView extends RecyclerView.Adapter<ProviderRecyclerView.ViewHolder> {
	private final Calendar calendar = Calendar.getInstance();
	private List<ProviderDto> providerDtoSet;
	private Long eventTime;
	private LayoutInflater mInflater;
	private ItemClickListener mClickListener;

	// Data is passed into the constructor
	public ProviderRecyclerView( Context context, Set<ProviderDto> providerDtoSet, Long eventTime ) {
		this.mInflater = LayoutInflater.from( context );
		this.providerDtoSet = new ArrayList<>( providerDtoSet );
		this.eventTime = eventTime * 1000;
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
		holder.providerView.setText( ( providerDto.getName() != null ) ? providerDto.getName() : providerDto.getCif() );
		if ( eventTime <= calendar.getTimeInMillis() ) {
			holder.deleteButtonView.setVisibility( View.GONE );
		}
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
		TextView providerView;
		ImageView deleteButtonView;

		ViewHolder( View itemView ) {
			super( itemView );
			providerView = itemView.findViewById( R.id.providerEventTextView );
			deleteButtonView = itemView.findViewById( R.id.btnDelentEventClient );
			providerView.setOnClickListener( this );
			deleteButtonView.setOnClickListener( this );
		}

		@Override
		public void onClick( View view ) {
			if ( mClickListener != null ) mClickListener.onItemClick( view, getAdapterPosition() );
		}
	}
}
