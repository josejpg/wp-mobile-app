package com.iessanvincente.weddingplanning.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iessanvincente.weddingplanning.R;
import com.iessanvincente.weddingplanning.domain.EventDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Jose J. Pardines
 */
public class EventsRecyclerView extends RecyclerView.Adapter<EventsRecyclerView.ViewHolder> {
	private List<EventDto> eventDtoSet;
	private LayoutInflater mInflater;
	private ItemClickListener mClickListener;

	// Data is passed into the constructor
	public EventsRecyclerView( Context context, Set<EventDto> eventDtoSet ) {
		this.mInflater = LayoutInflater.from( context );
		this.eventDtoSet = new ArrayList<>( eventDtoSet );
	}

	// Inflates the row layout from xml when needed
	@Override
	@NonNull
	public ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
		View view = mInflater.inflate( R.layout.recyclerview_event_item, parent, false );
		return new ViewHolder( view );
	}

	// Binds the data to the view and textview in each row
	@Override
	public void onBindViewHolder( @NonNull ViewHolder holder, int position ) {
		EventDto eventDto = eventDtoSet.get( position );
		holder.titleView.setText( eventDto.getEvent() );
		holder.descriptionView.setText( ( eventDto.getDescription().length() > 43 ) ? ( eventDto.getDescription().substring( 0, 40 ) + "..." ) : eventDto.getDescription() );
		holder.dateView.setText( Utils.getDateTimeAsString( eventDto.getDate() ) );
	}

	// Total number of rows
	@Override
	public int getItemCount( ) {
		return eventDtoSet.size();
	}

	// convenience method for getting data at click position
	public EventDto getItem( int id ) {
		return eventDtoSet.get( id );
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
		TextView titleView;
		TextView descriptionView;
		TextView dateView;

		ViewHolder( View itemView ) {
			super( itemView );
			titleView = itemView.findViewById( R.id.titleTextView );
			descriptionView = itemView.findViewById( R.id.descriptionTextView );
			dateView = itemView.findViewById( R.id.dateTextView );
			itemView.setOnClickListener( this );
		}

		@Override
		public void onClick( View view ) {
			if ( mClickListener != null ) mClickListener.onItemClick( view, getAdapterPosition() );
		}
	}
}
