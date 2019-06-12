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
import com.iessanvincente.weddingplanning.domain.ChatDto;
import com.iessanvincente.weddingplanning.domain.ClientDto;
import com.iessanvincente.weddingplanning.domain.EventDto;
import com.iessanvincente.weddingplanning.domain.ProviderDto;
import com.iessanvincente.weddingplanning.helper.MappingHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * @author Jose J. Pardines
 */
public class ChatRecyclerView extends RecyclerView.Adapter<ChatRecyclerView.ViewHolder> {
	private List<ChatDto> chatDtoList;
	private LayoutInflater mInflater;
	private ItemClickListener mClickListener;

	// Data is passed into the constructor
	public ChatRecyclerView( Context context, Set<ChatDto> chatDtoSet ) {
		this.mInflater = LayoutInflater.from( context );
		this.chatDtoList = new ArrayList<>( chatDtoSet );
	}

	// Inflates the row layout from xml when needed
	@Override
	@NonNull
	public ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
		View view = mInflater.inflate( R.layout.recyclerview_chat_item, parent, false );
		return new ViewHolder( view );
	}

	// Binds the data to the view and textview in each row
	@Override
	public void onBindViewHolder( @NonNull ViewHolder holder, int position ) {
		ChatDto chatDto = chatDtoList.get( position );
		EventDto eventDto = chatDto.getEvent();
		ProviderDto providerDto = chatDto.getProvider();
		holder.titleEventView.setText( eventDto.getEvent() );
		holder.dateEventView.setText( Utils.getDateAsString( eventDto.getDate() ) );
		holder.providerEventView.setText( providerDto.displayName() );
	}

	// Total number of rows
	@Override
	public int getItemCount( ) {
		return chatDtoList.size();
	}

	// convenience method for getting data at click position
	public ChatDto getItem( int id ) {
		return chatDtoList.get( id );
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
		TextView titleEventView;
		TextView dateEventView;
		TextView providerEventView;

		ViewHolder( View itemView ) {
			super( itemView );
			titleEventView = itemView.findViewById( R.id.titleEventChatTextView );
			dateEventView = itemView.findViewById( R.id.dateEventChatTextView );
			providerEventView = itemView.findViewById( R.id.providerEventChatTextView );
			itemView.setOnClickListener( this );
		}

		@Override
		public void onClick( View view ) {
			if ( mClickListener != null ) mClickListener.onItemClick( view, getAdapterPosition() );
		}
	}
}
