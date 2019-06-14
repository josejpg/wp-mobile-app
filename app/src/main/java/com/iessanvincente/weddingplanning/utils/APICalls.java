package com.iessanvincente.weddingplanning.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.iessanvincente.weddingplanning.R;
import com.iessanvincente.weddingplanning.domain.ChatDto;
import com.iessanvincente.weddingplanning.domain.ClientDto;
import com.iessanvincente.weddingplanning.domain.EventDto;
import com.iessanvincente.weddingplanning.entity.ClientesEntity;
import com.iessanvincente.weddingplanning.entity.EventosEntity;
import com.iessanvincente.weddingplanning.entity.MensajesEntity;
import com.iessanvincente.weddingplanning.helper.MappingHelper;
import com.iessanvincente.weddingplanning.interfaces.ClientsDtoCallbackInterface;
import com.iessanvincente.weddingplanning.interfaces.ResponseChatCallbackInterface;
import com.iessanvincente.weddingplanning.interfaces.ResponseClientCallbackInterface;
import com.iessanvincente.weddingplanning.interfaces.ResponseEventCallbackInterface;
import com.iessanvincente.weddingplanning.interfaces.ResponseMessageCallbackInterface;
import com.iessanvincente.weddingplanning.interfaces.ResponseProviderCallbackInterface;
import com.iessanvincente.weddingplanning.interfaces.ResponseServiceCallbackInterface;
import com.iessanvincente.weddingplanning.response.ResponseChat;
import com.iessanvincente.weddingplanning.response.ResponseClient;
import com.iessanvincente.weddingplanning.response.ResponseEvent;
import com.iessanvincente.weddingplanning.response.ResponseMessage;
import com.iessanvincente.weddingplanning.response.ResponseProvider;
import com.iessanvincente.weddingplanning.response.ResponseService;
import com.iessanvincente.weddingplanning.service.ChatService;
import com.iessanvincente.weddingplanning.service.ClientService;
import com.iessanvincente.weddingplanning.service.EventService;
import com.iessanvincente.weddingplanning.service.MessageService;
import com.iessanvincente.weddingplanning.service.ProviderService;
import com.iessanvincente.weddingplanning.service.ServiceService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Jose J. Pardines
 */
public class APICalls {
	private ClientService clientService = new ClientService();
	private ProviderService providerService = new ProviderService();
	private EventService eventService = new EventService();
	private ServiceService serviceService = new ServiceService();
	private ChatService chatService = new ChatService();
	private MessageService messageService = new MessageService();
	private String userToken;
	private Context context;

	public void setContext( Context context ) {
		this.context = context;
	}

	public void setUserToken( String userToken ) {
		this.userToken = userToken;
	}

	// Clients

	/**
	 * Geet client login by email and password
	 *
	 * @param email    client email
	 * @param password client password
	 * @param callback handled API response
	 */
	public void getLoginClient( String email, String password, ResponseClientCallbackInterface callback ) {

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

						// If isn't body in response call to onError
						// else get body and check it
						if ( response.body() != null ) {
							try {
								// Parse body in Response model
								Gson gson = new Gson();
								ResponseClient responseClient = gson.fromJson( response.body().string(), ResponseClient.class );

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
	 * Get client login by token
	 *
	 * @param callback handled API response
	 */
	public void getLoginClientByToken( ResponseClientCallbackInterface callback ) {

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

						// If isn't body in response call to onError
						// else get body and check it
						if ( response.body() != null ) {
							try {
								// Parse body in Response model
								Gson gson = new Gson();
								ResponseClient responseClient = gson.fromJson( response.body().string(), ResponseClient.class );

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
	 * @param clientesEntity client data
	 * @param callback       handled API response
	 */
	public void setNewClient( ClientesEntity clientesEntity, ResponseClientCallbackInterface callback ) {

		// Call to method in service
		clientService.registerClient(
				clientesEntity,
				new Callback<ResponseBody>() {
					/**
					 * If API response OK this method check data.
					 * @param call API call
					 * @param response API response
					 */
					@Override
					public void onResponse( Call<ResponseBody> call, Response<ResponseBody> response ) {

						// If isn't body in response call to onError
						// else get body and check it
						if ( response.body() != null ) {
							try {
								// Parse body in Response model
								Gson gson = new Gson();
								ResponseClient responseClient = gson.fromJson( response.body().string(), ResponseClient.class );

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
					 * @param call API call
					 * @param t API error
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
	 * @param clientesEntity client data
	 * @param callback       handled API response
	 */
	public void setUpdateClient( ClientesEntity clientesEntity, ResponseClientCallbackInterface callback ) {

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

						// If isn't body in response call to onError
						// else get body and check it
						if ( response.body() != null ) {
							try {
								// Parse body in Response model
								Gson gson = new Gson();
								ResponseClient responseClient = gson.fromJson( response.body().string(), ResponseClient.class );

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

	// Providers

	/**
	 * Get provider by ID
	 *
	 * @param callback handled API response
	 */
	public void getProviderById( String providerId, ResponseProviderCallbackInterface callback ) {

		// Call to method in service
		providerService.findById(
				userToken,
				providerId,
				new Callback<ResponseBody>() {
					/**
					 * If API response OK this method check data.
					 * @param call
					 * @param response
					 */
					@Override
					public void onResponse( Call<ResponseBody> call, Response<ResponseBody> response ) {

						// If isn't body in response call to onError
						// else get body and check it
						if ( response.body() != null ) {
							try {
								// Parse body in Response model
								Gson gson = new Gson();
								ResponseProvider responseProvider = gson.fromJson( response.body().string(), ResponseProvider.class );

								// If the response isn't successful call to onError
								if ( response.isSuccessful() ) {
									// If response getOk is true call to onSuccess
									// else call to onError
									if ( responseProvider.getOk() ) {
										callback.onSuccess( responseProvider );
									} else {
										callback.onError( responseProvider.getError() );
									}
								} else {
									callback.onError( responseProvider.getError() );
								}
							} catch (IOException e) {
								callback.onError( context.getResources().getString( R.string.toast_provider_find_failed ) );
								e.printStackTrace();
							}
						} else {
							callback.onError( context.getResources().getString( R.string.toast_provider_find_failed ) );
						}
					}

					/**
					 *  IF API call failed this method call to onError and stop the progress dialog.
					 * @param call
					 * @param t
					 */
					@Override
					public void onFailure( Call call, Throwable t ) {
						callback.onError( context.getResources().getString( R.string.toast_provider_find_failed ) );
					}
				}
		);
	}

	/**
	 * Get provider by ID
	 *
	 * @param callback handled API response
	 */
	public void getProviderByServiceId( String serviceId, ResponseProviderCallbackInterface callback ) {

		// Call to method in service
		providerService.findByServiceId(
				userToken,
				serviceId,
				new Callback<ResponseBody>() {
					/**
					 * If API response OK this method check data.
					 * @param call
					 * @param response
					 */
					@Override
					public void onResponse( Call<ResponseBody> call, Response<ResponseBody> response ) {

						// If isn't body in response call to onError
						// else get body and check it
						if ( response.body() != null ) {
							try {
								// Parse body in Response model
								Gson gson = new Gson();
								ResponseProvider responseProvider = gson.fromJson( response.body().string(), ResponseProvider.class );

								// If the response isn't successful call to onError
								if ( response.isSuccessful() ) {
									// If response getOk is true call to onSuccess
									// else call to onError
									if ( responseProvider.getOk() ) {
										callback.onSuccess( responseProvider );
									} else {
										callback.onError( responseProvider.getError() );
									}
								} else {
									callback.onError( responseProvider.getError() );
								}
							} catch (IOException e) {
								callback.onError( context.getResources().getString( R.string.toast_provider_find_failed ) );
								e.printStackTrace();
							}
						} else {
							callback.onError( context.getResources().getString( R.string.toast_provider_find_failed ) );
						}
					}

					/**
					 *  IF API call failed this method call to onError and stop the progress dialog.
					 * @param call
					 * @param t
					 */
					@Override
					public void onFailure( Call call, Throwable t ) {
						callback.onError( context.getResources().getString( R.string.toast_provider_find_failed ) );
					}
				}
		);
	}

	// Events

	/**
	 * Get event by ID
	 *
	 * @param callback handled API response
	 */
	public void getEventById( Long eventId, ResponseEventCallbackInterface callback ) {

		// Call to method in service
		eventService.getEventById(
				userToken,
				eventId,
				new Callback<ResponseBody>() {
					/**
					 * If API response OK this method check data.
					 * @param call API call
					 * @param response API response
					 */
					@Override
					public void onResponse( Call<ResponseBody> call, Response<ResponseBody> response ) {

						// If isn't body in response call to onError
						// else get body and check it
						if ( response.body() != null ) {
							try {
								// Parse body in Response model
								Gson gson = new Gson();
								ResponseEvent responseEvent = gson.fromJson( response.body().string(), ResponseEvent.class );

								// If the response isn't successful call to onError
								if ( response.isSuccessful() ) {
									// If response getOk is true call to onSuccess
									// else call to onError
									if ( responseEvent.getOk() ) {
										callback.onSuccess( responseEvent );
									} else {
										callback.onError( responseEvent.getError() );
									}
								} else {
									callback.onError( responseEvent.getError() );
								}
							} catch (IOException e) {
								callback.onError( context.getResources().getString( R.string.toast_event_find_failed ) );
								e.printStackTrace();
							}
						} else {
							callback.onError( context.getResources().getString( R.string.toast_event_find_failed ) );
						}
					}

					/**
					 *  IF API call failed this method call to onError and stop the progress dialog.
					 * @param call API call
					 * @param t API error
					 */
					@Override
					public void onFailure( Call call, Throwable t ) {
						callback.onError( context.getResources().getString( R.string.toast_event_find_failed ) );
					}
				}
		);
	}

	/**
	 * Get events by client
	 *
	 * @param clientDto client data
	 * @param callback  handled API response
	 */
	public void getEventsByClient( ClientDto clientDto, ClientsDtoCallbackInterface callback ) {
		// Call to method in service
		eventService.getEventsByClient(
				userToken,
				clientDto.getId(),
				new Callback<ResponseBody>() {
					/**
					 * If API response OK this method check data.
					 * @param call API call
					 * @param response API response
					 */
					@Override
					public void onResponse( Call<ResponseBody> call, Response<ResponseBody> response ) {

						// If isn't body in response call to onError
						// else get body and check it
						if ( response.body() != null ) {
							try {
								// Parse body in Response model
								Gson gson = new Gson();
								ResponseEvent responseEvent = gson.fromJson( response.body().string(), ResponseEvent.class );

								// If the response is successful
								if ( response.isSuccessful() ) {
									// If response getOk
									if ( responseEvent.getOk() ) {
										Set<EventDto> events = new HashSet<>();
										Set<EventosEntity> eventosEntitySet = responseEvent.getEvents();
										List<EventosEntity> eventosEntityList = new ArrayList<>( eventosEntitySet );
										if ( eventosEntitySet.size() > 0 ) {
											for ( EventosEntity evento : eventosEntitySet ) {
												getEventById(
														evento.getId(),
														new ResponseEventCallbackInterface() {
															@Override
															public void onSuccess( ResponseEvent responseEventById ) {
																events.add( MappingHelper.getEventDtoFromEventosEntity( responseEventById.getEvent() ) );
																clientDto.setEvents( events );
																if ( evento == eventosEntityList.get( eventosEntityList.size() - 1 ) ) {
																	callback.onSuccess( clientDto );
																}
															}

															@Override
															public void onError( String message ) {

															}
														}
												);
											}
										} else {
											callback.onError( responseEvent.getError() );
										}
									} else {
										callback.onError( responseEvent.getError() );
									}
								}
							} catch (IOException e) {
								callback.onError( e.getMessage() );
							}
						}
					}

					/**
					 *  If API call failed.
					 * @param call API call
					 * @param t API error
					 */
					@Override
					public void onFailure( Call call, Throwable t ) {
						callback.onError( t.getMessage() );
					}
				}
		);
	}

	/**
	 * Update data event
	 *
	 * @param eventosEntity event data
	 * @param callback      handled API response
	 */
	public void setUpdateEvent( EventosEntity eventosEntity, ResponseEventCallbackInterface callback ) {
		// Call to method in service
		eventService.updateEvent(
				userToken,
				eventosEntity,
				new Callback<ResponseBody>() {
					/**
					 * If API response OK this method check data.
					 * @param call API call
					 * @param response API response
					 */
					@Override
					public void onResponse( Call<ResponseBody> call, Response<ResponseBody> response ) {

						// If isn't body in response call to onError
						// else get body and check it
						if ( response.body() != null ) {
							try {
								// Parse body in Response model
								Gson gson = new Gson();
								ResponseEvent responseEvent = gson.fromJson( response.body().string(), ResponseEvent.class );

								// If the response is successful
								if ( response.isSuccessful() ) {
									// If response getOk
									if ( responseEvent.getOk() ) {
										callback.onSuccess( responseEvent );
									} else {
										callback.onError( responseEvent.getError() );
									}
								}
							} catch (IOException e) {
								callback.onError( e.getMessage() );
							}
						}
					}

					/**
					 *  If API call failed.
					 * @param call API call
					 * @param t API error
					 */
					@Override
					public void onFailure( Call call, Throwable t ) {
						callback.onError( t.getMessage() );
					}
				}
		);
	}

	/**
	 * Create new event
	 *
	 * @param eventosEntity event data
	 * @param callback      handled API response
	 */
	public void setNewEvent( EventosEntity eventosEntity, ResponseEventCallbackInterface callback ) {
		// Call to method in service
		eventService.createEvent(
				userToken,
				eventosEntity,
				new Callback<ResponseBody>() {
					/**
					 * If API response OK this method check data.
					 * @param call API call
					 * @param response API response
					 */
					@Override
					public void onResponse( Call<ResponseBody> call, Response<ResponseBody> response ) {

						// If isn't body in response call to onError
						// else get body and check it
						if ( response.body() != null ) {
							try {
								// Parse body in Response model
								Gson gson = new Gson();
								ResponseEvent responseEvent = gson.fromJson( response.body().string(), ResponseEvent.class );

								// If the response is successful
								if ( response.isSuccessful() ) {
									// If response getOk
									if ( responseEvent.getOk() ) {
										callback.onSuccess( responseEvent );
									} else {
										callback.onError( responseEvent.getError() );
									}
								}
							} catch (IOException e) {
								callback.onError( e.getMessage() );
							}
						}
					}

					/**
					 *  If API call failed.
					 * @param call API call
					 * @param t API error
					 */
					@Override
					public void onFailure( Call call, Throwable t ) {
						callback.onError( t.getMessage() );
					}
				}
		);
	}


	// Services

	/**
	 * Get all services
	 *
	 * @param callback handled API response
	 */
	public void getServices( ResponseServiceCallbackInterface callback ) {
		// Call to method in service
		serviceService.getServices(
				userToken,
				new Callback<ResponseBody>() {
					/**
					 * If API response OK this method check data.
					 * @param call API call
					 * @param response API response
					 */
					@Override
					public void onResponse( Call<ResponseBody> call, Response<ResponseBody> response ) {

						// If isn't body in response call to onError
						// else get body and check it
						if ( response.body() != null ) {
							try {
								// Parse body in Response model
								Gson gson = new Gson();
								ResponseService responseService = gson.fromJson( response.body().string(), ResponseService.class );

								// If the response is successful
								if ( response.isSuccessful() ) {
									// If response getOk
									if ( responseService.getOk() ) {
										callback.onSuccess( responseService );
									} else {
										callback.onError( responseService.getError() );
									}
								}
							} catch (IOException e) {
								callback.onError( e.getMessage() );
							}
						}
					}

					/**
					 *  If API call failed.
					 * @param call API call
					 * @param t API error
					 */
					@Override
					public void onFailure( Call call, Throwable t ) {
						callback.onError( t.getMessage() );
					}
				}
		);
	}

	// Chats

	/**
	 * Get all chats for a client
	 *
	 * @param clientDto client data
	 * @param callback handled API response
	 */
	public void getChats( ClientDto clientDto, ResponseChatCallbackInterface callback ) {
		// Call to method in service
		chatService.getChats(
				userToken,
				clientDto.getId().toString(),
				new Callback<ResponseBody>() {
					/**
					 * If API response OK this method check data.
					 * @param call API call
					 * @param response API response
					 */
					@Override
					public void onResponse( Call<ResponseBody> call, Response<ResponseBody> response ) {

						// If isn't body in response call to onError
						// else get body and check it
						if ( response.body() != null ) {
							try {
								// Parse body in Response model
								Gson gson = new Gson();
								ResponseChat responseChat = gson.fromJson( response.body().string(), ResponseChat.class );

								// If the response is successful
								if ( response.isSuccessful() ) {
									// If response getOk
									if ( responseChat.getOk() ) {
										callback.onSuccess( responseChat );
									} else {
										callback.onError( responseChat.getError() );
									}
								}
							} catch (IOException e) {
								callback.onError( e.getMessage() );
							}
						}
					}

					/**
					 *  If API call failed.
					 * @param call API call
					 * @param t API error
					 */
					@Override
					public void onFailure( Call call, Throwable t ) {
						callback.onError( t.getMessage() );
					}
				}
		);
	}

	/**
	 * Get all messages for a chat
	 *
	 * @param chatDto event data
	 * @param callback handled API response
	 */
	public void getMessages( ChatDto chatDto, ResponseMessageCallbackInterface callback ) {
		// Call to method in service
		messageService.getMessages(
				userToken,
				chatDto.getEvent().getId().toString(),
				new Callback<ResponseBody>() {
					/**
					 * If API response OK this method check data.
					 * @param call API call
					 * @param response API response
					 */
					@Override
					public void onResponse( Call<ResponseBody> call, Response<ResponseBody> response ) {

						// If isn't body in response call to onError
						// else get body and check it
						if ( response.body() != null ) {
							try {
								// Parse body in Response model
								Gson gson = new Gson();
								ResponseMessage responseMessage = gson.fromJson( response.body().string(), ResponseMessage.class );

								// If the response is successful
								if ( response.isSuccessful() ) {
									// If response getOk
									if ( responseMessage.getOk() ) {
										callback.onSuccess( responseMessage );
									} else {
										callback.onError( responseMessage.getError() );
									}
								}
							} catch (IOException e) {
								callback.onError( e.getMessage() );
							}
						}
					}

					/**
					 *  If API call failed.
					 * @param call API call
					 * @param t API error
					 */
					@Override
					public void onFailure( Call call, Throwable t ) {
						callback.onError( t.getMessage() );
					}
				}
		);
	}

	/**
	 * Send a new message
	 *
	 * @param mensajesEntity message data
	 * @param callback handled API response
	 */
	public void sendMessage( MensajesEntity mensajesEntity, ResponseMessageCallbackInterface callback ) {
		// Call to method in service
		messageService.sendMessage(
				userToken,
				mensajesEntity,
				new Callback<ResponseBody>() {
					/**
					 * If API response OK this method check data.
					 * @param call API call
					 * @param response API response
					 */
					@Override
					public void onResponse( Call<ResponseBody> call, Response<ResponseBody> response ) {

						// If isn't body in response call to onError
						// else get body and check it
						if ( response.body() != null ) {
							try {
								// Parse body in Response model
								Gson gson = new Gson();
								ResponseMessage responseMessage = gson.fromJson( response.body().string(), ResponseMessage.class );

								// If the response is successful
								if ( response.isSuccessful() ) {
									// If response getOk
									if ( responseMessage.getOk() ) {
										callback.onSuccess( responseMessage );
									} else {
										callback.onError( responseMessage.getError() );
									}
								}
							} catch (IOException e) {
								callback.onError( e.getMessage() );
							}
						}
					}

					/**
					 *  If API call failed.
					 * @param call API call
					 * @param t API error
					 */
					@Override
					public void onFailure( Call call, Throwable t ) {
						callback.onError( t.getMessage() );
					}
				}
		);
	}
}
