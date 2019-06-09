package com.iessanvincente.weddingplanning.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.iessanvincente.weddingplanning.R;
import com.iessanvincente.weddingplanning.domain.ClientDto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class ClientsRecyclerView extends RecyclerView.Adapter<ClientsRecyclerView.ViewHolder> {
	private final Calendar calendar = Calendar.getInstance();
	private List<ClientDto> clientDtoSet;
	private Long eventTime;
	private LayoutInflater mInflater;
	private ItemClickListener mClickListener;

	// Data is passed into the constructor
	public ClientsRecyclerView( Context context, Set<ClientDto> clientDtoSet, Long eventTime ) {
		this.mInflater = LayoutInflater.from( context );
		this.clientDtoSet = new ArrayList<>( clientDtoSet );
		this.eventTime = eventTime;
	}

	// Inflates the row layout from xml when needed
	@Override
	@NonNull
	public ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
		View view = mInflater.inflate( R.layout.recyclerview_client_item, parent, false );
		return new ViewHolder( view );
	}

	// Binds the data to the view and textview in each row
	@Override
	public void onBindViewHolder( @NonNull ViewHolder holder, int position ) {
		ClientDto clientDto = clientDtoSet.get( position );
		holder.clientView.setText( clientDto.displayName() );
		if ( eventTime <= calendar.getTimeInMillis() ) {
			holder.deleteButtonView.setVisibility( View.GONE );
		}
	}

	// Total number of rows
	@Override
	public int getItemCount( ) {
		return clientDtoSet.size();
	}

	// convenience method for getting data at click position
	public ClientDto getItem( int id ) {
		return clientDtoSet.get( id );
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
