package com.iessanvincente.weddingplanning.utils;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.iessanvincente.weddingplanning.R;
import com.iessanvincente.weddingplanning.domain.TodoDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Jose J. Pardines
 */
public class TodoRecyclerView extends RecyclerView.Adapter<TodoRecyclerView.ViewHolder> {
	private List<TodoDto> todoDtoList;
	private LayoutInflater mInflater;
	private ItemClickListener mClickListener;

	// Data is passed into the constructor
	public TodoRecyclerView( Context context, Set<TodoDto> todoDtoSet ) {
		this.mInflater = LayoutInflater.from( context );
		this.todoDtoList = new ArrayList<>( todoDtoSet );
	}

	// Inflates the row layout from xml when needed
	@Override
	@NonNull
	public TodoRecyclerView.ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
		View view = mInflater.inflate( R.layout.recyclerview_todo_item, parent, false );
		return new TodoRecyclerView.ViewHolder( view );
	}

	// Binds the data to the view and textview in each row
	@Override
	public void onBindViewHolder( @NonNull TodoRecyclerView.ViewHolder holder, int position ) {
		TodoDto todoDto = todoDtoList.get( position );
		holder.todoView.setText( todoDto.getTodo() );
		holder.checkView.setChecked( todoDto.getDone() );
		if ( todoDto.getDone() ) {
			holder.todoView.setPaintFlags( holder.todoView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG );
		}
		holder.checkView.setOnCheckedChangeListener( ( buttonView, isChecked ) -> {
			todoDtoList.get( position ).setDone( isChecked );
		} );
	}

	// Total number of rows
	@Override
	public int getItemCount( ) {
		return todoDtoList.size();
	}

	// convenience method for getting data at click position
	public TodoDto getItem( int id ) {
		return todoDtoList.get( id );
	}

	// allows clicks events to be caught
	public void setClickListener( TodoRecyclerView.ItemClickListener itemClickListener ) {
		this.mClickListener = itemClickListener;
	}

	// parent activity will implement this method to respond to click events
	public interface ItemClickListener {
		void onItemClick( View view, int position );
	}

	// Stores and recycles views as they are scrolled off screen
	public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		MaterialCheckBox checkView;
		TextView todoView;
		ImageView buttonView;

		ViewHolder( View itemView ) {
			super( itemView );
			checkView = itemView.findViewById( R.id.check_todo );
			todoView = itemView.findViewById( R.id.todoTextView );
			buttonView = itemView.findViewById( R.id.btn_remove_todo );
			checkView.setOnClickListener( this );
			buttonView.setOnClickListener( this );
		}

		@Override
		public void onClick( View view ) {
			if ( mClickListener != null ) mClickListener.onItemClick( view, getAdapterPosition() );
		}
	}
}
