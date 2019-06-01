package com.iessanvincente.weddingplanning.utils;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.iessanvincente.weddingplanning.R;
import com.iessanvincente.weddingplanning.domain.ClientDto;
import com.iessanvincente.weddingplanning.domain.EventDto;
import com.iessanvincente.weddingplanning.entity.ClientesEntity;
import com.iessanvincente.weddingplanning.entity.EventosEntity;
import com.iessanvincente.weddingplanning.helper.MappingHelper;
import com.iessanvincente.weddingplanning.interfaces.ClientesEntityCallbackInterface;
import com.iessanvincente.weddingplanning.interfaces.ClientsDtoCallbackInterface;
import com.iessanvincente.weddingplanning.interfaces.ResponseClientCallbackInterface;
import com.iessanvincente.weddingplanning.response.ResponseClient;
import com.iessanvincente.weddingplanning.response.ResponseEvent;
import com.iessanvincente.weddingplanning.service.ClientService;
import com.iessanvincente.weddingplanning.service.EventService;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APICalls {
	private ClientService clientService = new ClientService();
	private EventService eventService = new EventService();
	private String userToken;
	private Context context;

	public void setContext( Context context ) {
		this.context = context;
	}
	public void setUserToken( String userToken ) {
		this.userToken = userToken;
	}

	/**
	 * Geet login by email and password
	 *
	 * @param email
	 * @param password
	 * @param callback
	 */
	public void getLoginClient( String email, String password, ResponseClientCallbackInterface callback ){

		// Call to method in service
		clientService.login(
				email,
				password,
				new Callback<ResponseBody>() {
					/**
					 * If API response OK this method check data.
					 * @param call
					 * @param response
					 */
					@Override
					public void onResponse( Call<ResponseBody> call, Response<ResponseBody> response ) {

						Gson gson = new Gson();
						ResponseClient responseClient = null;

						// If isn't body in response call to onError
						// else get body and check it
						if ( response.body() != null ) {
							try {
								// Parse boty in ResponseClient model
								responseClient = gson.fromJson( response.body().string(), ResponseClient.class );

								// If the response isn't successful call to onError
								if ( response.isSuccessful() ) {
									// If response getOk is true call to onSuccess
									// else call to onError
									if ( responseClient.getOk() ) {

										callback.onSuccess( responseClient );
									} else {
										callback.onError( responseClient.getError() );
									}
								} else {
									callback.onError( responseClient.getError() );
								}
							} catch (IOException e) {
								callback.onError( context.getResources().getString( R.string.toast_client_login_failed ) );
								e.printStackTrace();
							}
						} else {
							callback.onError( context.getResources().getString( R.string.toast_client_login_failed ) );
						}
					}

					/**
					 *  IF API call failed this method call to onError and stop the progress dialog.
					 * @param call
					 * @param t
					 */
					@Override
					public void onFailure( Call call, Throwable t ) {
						callback.onError( context.getResources().getString( R.string.toast_client_login_failed ) );
					}
				}
		);
	}

	/**
	 * Get login by token
	 *
	 * @param callback
	 */
	public void getLoginClientByToken( ResponseClientCallbackInterface callback ){

		// Call to method in service
		clientService.loginByToken(
				userToken,
				new Callback<ResponseBody>() {
					/**
					 * If API response OK this method check data.
					 * @param call
					 * @param response
					 */
					@Override
					public void onResponse( Call<ResponseBody> call, Response<ResponseBody> response ) {

						Gson gson = new Gson();
						ResponseClient responseClient = null;

						// If isn't body in response call to onError
						// else get body and check it
						if ( response.body() != null ) {
							try {
								// Parse boty in ResponseClient model
								responseClient = gson.fromJson( response.body().string(), ResponseClient.class );

								// If the response isn't successful call to onError
								if ( response.isSuccessful() ) {
									// If response getOk is true call to onSuccess
									// else call to onError
									if ( responseClient.getOk() ) {

										callback.onSuccess( responseClient );
									} else {
										callback.onError( responseClient.getError() );
									}
								} else {
									callback.onError( responseClient.getError() );
								}
							} catch (IOException e) {
								callback.onError( context.getResources().getString( R.string.toast_client_login_failed ) );
								e.printStackTrace();
							}
						} else {
							callback.onError( context.getResources().getString( R.string.toast_client_login_failed ) );
						}
					}

					/**
					 *  IF API call failed this method call to onError and stop the progress dialog.
					 * @param call
					 * @param t
					 */
					@Override
					public void onFailure( Call call, Throwable t ) {
						callback.onError( context.getResources().getString( R.string.toast_client_login_failed ) );
					}
				}
		);
	}

	/**
	 * Signup new client
	 *
	 * @param clientesEntity
	 * @param callback
	 */
	public void setNewClient( ClientesEntity clientesEntity, ResponseClientCallbackInterface callback ){

		// Call to method in service
		clientService.registerClient(
				clientesEntity,
				new Callback<ResponseBody>() {
					/**
					 * If API response OK this method check data.
					 * @param call
					 * @param response
					 */
					@Override
					public void onResponse( Call<ResponseBody> call, Response<ResponseBody> response ) {

						Gson gson = new Gson();
						ResponseClient responseClient = null;

						// If isn't body in response call to onError
						// else get body and check it
						if ( response.body() != null ) {
							try {
								// Parse boty in ResponseClient model
								responseClient = gson.fromJson( response.body().string(), ResponseClient.class );

								// If the response isn't successful call to onError
								if ( response.isSuccessful() ) {
									// If response getOk is true call to onSuccess
									// else call to onError
									if ( responseClient.getOk() ) {

										callback.onSuccess( responseClient );
									} else {
										callback.onError( responseClient.getError() );
									}
								} else {
									callback.onError( responseClient.getError() );
								}
							} catch (IOException e) {
								callback.onError( context.getResources().getString( R.string.toast_client_signup_failed ) );
								e.printStackTrace();
							}
						} else {
							callback.onError( context.getResources().getString( R.string.toast_client_signup_failed ) );
						}
					}

					/**
					 *  IF API call failed this method call to onError and stop the progress dialog.
					 * @param call
					 * @param t
					 */
					@Override
					public void onFailure( Call call, Throwable t ) {
						callback.onError( context.getResources().getString( R.string.toast_client_signup_failed ) );
					}
				}
		);
	}
	
	/**
	 * Update client
	 *
	 * @param clientesEntity
	 * @param callback
	 */
	public void setUpdateClient( ClientesEntity clientesEntity, ClientesEntityCallbackInterface callback ){

		// Call to method in service
		clientService.updateClient(
				userToken,
				clientesEntity,
				new Callback<ResponseBody>() {
					/**
					 * If API response OK this method check data.
					 * @param call
					 * @param response
					 */
					@Override
					public void onResponse( Call<ResponseBody> call, Response<ResponseBody> response ) {

						Gson gson = new Gson();
						ResponseClient responseClient = null;

						// If isn't body in response call to onError
						// else get body and check it
						if ( response.body() != null ) {
							try {
								// Parse boty in ResponseClient model
								responseClient = gson.fromJson( response.body().string(), ResponseClient.class );

								// If the response isn't successful call to onError
								if ( response.isSuccessful() ) {
									// If response getOk is true call to onSuccess
									// else call to onError
									if ( responseClient.getOk() ) {

										callback.onSuccess( clientesEntity );
									} else {
										callback.onError( responseClient.getError() );
									}
								} else {
									callback.onError( responseClient.getError() );
								}
							} catch (IOException e) {
								callback.onError( context.getResources().getString( R.string.progressDialog_updating_profile ) );
								e.printStackTrace();
							}
						} else {
							callback.onError( context.getResources().getString( R.string.progressDialog_updating_profile ) );
						}
					}

					/**
					 *  IF API call failed this method call to onError and stop the progress dialog.
					 * @param call
					 * @param t
					 */
					@Override
					public void onFailure( Call call, Throwable t ) {
						callback.onError( context.getResources().getString( R.string.toast_client_login_failed ) );
					}
				}
		);
	}

	/**
	 * Get events by client
	 *
	 * @param clientDto
	 * @param callback
	 */
	public void getEvenetsByClient( ClientDto clientDto, ClientsDtoCallbackInterface callback ) {
		// Call to method in service
		eventService.getEventsByClient(
				userToken,
				clientDto.getId(),
				new Callback<ResponseBody>() {
					/**
					 * If API response OK this method check data.
					 * @param call
					 * @param response
					 */
					@Override
					public void onResponse( Call<ResponseBody> call, Response<ResponseBody> response ) {

						Gson gson = new Gson();
						ResponseEvent responseEvent = null;

						// If isn't body in response call to onError
						// else get body and check it
						if ( response.body() != null ) {
							try {
								// Parse boty in ResponseClient model
								responseEvent = gson.fromJson( response.body().string(), ResponseEvent.class );

								// If the response is successful
								if ( response.isSuccessful() ) {
									// If response getOk
									if ( responseEvent.getOk() ) {
										Set<EventDto> events = new HashSet<>();
										Set<EventosEntity> eventosEntitySet = responseEvent.getEvents();
										for ( EventosEntity evento : eventosEntitySet ) {
											events.add( MappingHelper.getEventDtoFromEventosEntity( evento ) );
										}
										clientDto.setEvents( events );
										callback.onSuccess( clientDto );
									}
								}
							} catch (IOException e) {
							}
						}
					}

					/**
					 *  If API call failed.
					 * @param call
					 * @param t
					 */
					@Override
					public void onFailure( Call call, Throwable t ) {
						callback.onError( t.getMessage() );
					}
				}
		);
	}

}
