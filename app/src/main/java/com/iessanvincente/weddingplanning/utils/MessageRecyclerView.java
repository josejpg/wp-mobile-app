package com.iessanvincente.weddingplanning.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iessanvincente.weddingplanning.R;
import com.iessanvincente.weddingplanning.domain.MessageDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Jose J. Pardines
 */
public class MessageRecyclerView extends RecyclerView.Adapter<MessageRecyclerView.ViewHolder> {
	private List<MessageDto> messageDtoList;
	private LayoutInflater mInflater;
	private ItemClickListener mClickListener;
	private Context context;

	// Data is passed into the constructor
	public MessageRecyclerView( Context context, Set<MessageDto> messageDtoSet ) {
		this.mInflater = LayoutInflater.from( context );
		this.context = context;
		this.messageDtoList = new ArrayList<>( messageDtoSet );
	}

	// Inflates the row layout from xml when needed
	@Override
	@NonNull
	public ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
		View view = mInflater.inflate( R.layout.recyclerview_message_item, parent, false );
		return new ViewHolder( view );
	}

	// Binds the data to the view and textview in each row
	@Override
	public void onBindViewHolder( @NonNull ViewHolder holder, int position ) {
		boolean isAuto = false;
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT );
		MessageDto messageDto = messageDtoList.get( position );
		String name = "";
		if ( messageDto.getClient() != null ) {
			name = messageDto.getClient().displayName();
		} else if ( messageDto.getProvider() != null ) {
			name = messageDto.getProvider().displayName();
		} else {
			isAuto = true;
		}

		holder.nameMessageView.setText( name );
		holder.messageView.setText( messageDto.getMessage() );
		holder.dateView.setText( Utils.getDateTimeAsString( messageDto.getDate() ) );

		if ( isAuto ) {
			holder.bubbleLayout.setBackground( context.getDrawable( R.drawable.background_message_auto ) );
			holder.nameMessageView.setVisibility( View.GONE );
			holder.dateView.setVisibility( View.GONE );
			holder.messageView.setTextColor( context.getResources().getColor( R.color.white ) );
			params.gravity = Gravity.CENTER;
			params.leftMargin = 5;
			params.rightMargin = 5;
		} else {
			if ( messageDto.getOwner() ) {
				holder.bubbleLayout.setBackground( context.getDrawable( R.drawable.background_message_own ) );
				params.gravity = Gravity.END;
			} else {
				holder.bubbleLayout.setBackground( context.getDrawable( R.drawable.background_message ) );
				params.gravity = Gravity.START;
			}
		}
		holder.bubbleLayout.setLayoutParams( params );
		holder.nameMessageView.setLayoutParams( params );
		holder.messageView.setLayoutParams( params );
		holder.dateView.setLayoutParams( params );
	}

	// Total number of rows
	@Override
	public int getItemCount( ) {
		return messageDtoList.size();
	}

	// convenience method for getting data at click position
	public MessageDto getItem( int id ) {
		return messageDtoList.get( id );
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
		TextView nameMessageView;
		TextView messageView;
		TextView dateView;
		Space spaceBubbles;
		LinearLayout bubbleLayout;

		ViewHolder( View itemView ) {
			super( itemView );
			nameMessageView = itemView.findViewById( R.id.nameMessageTextView );
			messageView = itemView.findViewById( R.id.messageTextView );
			dateView = itemView.findViewById( R.id.dateMessageTextView );
			spaceBubbles = itemView.findViewById( R.id.spaceBubbles );
			bubbleLayout = itemView.findViewById( R.id.bubbleLayout );
			itemView.setOnClickListener( this );
		}

		@Override
		public void onClick( View view ) {
			if ( mClickListener != null ) mClickListener.onItemClick( view, getAdapterPosition() );
		}
	}
}
