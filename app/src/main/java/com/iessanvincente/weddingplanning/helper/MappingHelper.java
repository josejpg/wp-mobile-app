package com.iessanvincente.weddingplanning.helper;

import android.util.Log;

import androidx.annotation.NonNull;

import com.iessanvincente.weddingplanning.domain.ChatDto;
import com.iessanvincente.weddingplanning.domain.ClientDto;
import com.iessanvincente.weddingplanning.domain.EventDto;
import com.iessanvincente.weddingplanning.domain.MessageDto;
import com.iessanvincente.weddingplanning.domain.ProviderDto;
import com.iessanvincente.weddingplanning.domain.ServiceDto;
import com.iessanvincente.weddingplanning.domain.TodoDto;
import com.iessanvincente.weddingplanning.entity.ChatsEntity;
import com.iessanvincente.weddingplanning.entity.ClientesEntity;
import com.iessanvincente.weddingplanning.entity.EventosEntity;
import com.iessanvincente.weddingplanning.entity.MensajesEntity;
import com.iessanvincente.weddingplanning.entity.ProveedoresEntity;
import com.iessanvincente.weddingplanning.entity.ServiciosEntity;
import com.iessanvincente.weddingplanning.entity.TodoEntity;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Jose J. Pardines
 */
public class MappingHelper {

	/**
	 * Map from ClientesEntity to ClientDto
	 *
	 * @param clientesEntity data
	 * @return ClientDto
	 */
	public static ClientDto getClientDtoFromClientesEntity( @NonNull ClientesEntity clientesEntity ) {
		Log.println( Log.INFO, "Mapper", "getClientDtoFromClientesEntity" );
		ClientDto clientDto = new ClientDto();
		clientDto.setId( clientesEntity.getId() );
		clientDto.setDni( clientesEntity.getDni() );
		clientDto.setName( clientesEntity.getNombre() );
		clientDto.setLastName( clientesEntity.getApellidos() );
		clientDto.setEmail( clientesEntity.getEmail() );
		clientDto.setAddress( clientesEntity.getDireccion() );
		clientDto.setTown( clientesEntity.getPoblacion() );
		clientDto.setProvince( clientesEntity.getProvincia() );
		clientDto.setPostalCode( clientesEntity.getCp() );
		clientDto.setPhone( clientesEntity.getTelefono() );
		clientDto.setMobile( clientesEntity.getMovil() );
		clientDto.setBirthDate( clientesEntity.getFnac() );
		return clientDto;
	}

	/**
	 * Map from ClientDto to ClientesEntity
	 *
	 * @param clientDto data
	 * @return ClientesEntity
	 */
	public static ClientesEntity getClientesEntityFromClientDto( @NonNull ClientDto clientDto ) {
		Log.println( Log.INFO, "Mapper", "getClientesEntityFromClientDto" );
		ClientesEntity clientesEntity = new ClientesEntity();
		clientesEntity.setId( clientDto.getId() );
		clientesEntity.setDni( clientDto.getDni() );
		clientesEntity.setNombre( clientDto.getName() );
		clientesEntity.setApellidos( clientDto.getName() );
		clientesEntity.setEmail( clientDto.getEmail() );
		clientesEntity.setDireccion( clientDto.getAddress() );
		clientesEntity.setPoblacion( clientDto.getTown() );
		clientesEntity.setProvincia( clientDto.getProvince() );
		clientesEntity.setCp( clientDto.getPostalCode() );
		clientesEntity.setTelefono( clientDto.getPhone() );
		clientesEntity.setMovil( clientDto.getMobile() );
		clientesEntity.setFnac( clientDto.getBirthDate() );
		return clientesEntity;
	}

	/**
	 * Map from ProveedoresEntity to ProviderDto
	 *
	 * @param proveedoresEntity data
	 * @return ProviderDto
	 */
	public static ProviderDto getProviderDtoFromProveedoresEntity( @NonNull ProveedoresEntity proveedoresEntity ) {
		Log.println( Log.INFO, "Mapper", "getClientDtoFromClientesEntity" );
		ProviderDto providerDto = new ProviderDto();
		providerDto.setId( proveedoresEntity.getId() );
		providerDto.setCif( proveedoresEntity.getCif() );
		providerDto.setName( proveedoresEntity.getNombre() );
		providerDto.setEmail( proveedoresEntity.getEmail() );
		providerDto.setAddress( proveedoresEntity.getDireccion() );
		providerDto.setTown( proveedoresEntity.getPoblacion() );
		providerDto.setProvince( proveedoresEntity.getProvincia() );
		providerDto.setPostalCode( proveedoresEntity.getCp() );
		providerDto.setPhone( proveedoresEntity.getTelefono() );
		providerDto.setMobile( proveedoresEntity.getMovil() );
		return providerDto;
	}

	/**
	 * Map from ProviderDto to ProveedoresEntity
	 *
	 * @param providerDto data
	 * @return ProveedoresEntity
	 */
	public static ProveedoresEntity getProveedoresEntityFromProviderDto( @NonNull ProviderDto providerDto ) {
		Log.println( Log.INFO, "Mapper", "getProveedoresEntityFromProviderDto" );
		ProveedoresEntity proveedoresEntity = new ProveedoresEntity();
		proveedoresEntity.setId( providerDto.getId() );
		proveedoresEntity.setCif( providerDto.getCif() );
		proveedoresEntity.setNombre( providerDto.getName() );
		proveedoresEntity.setEmail( providerDto.getEmail() );
		proveedoresEntity.setDireccion( providerDto.getAddress() );
		proveedoresEntity.setPoblacion( providerDto.getTown() );
		proveedoresEntity.setProvincia( providerDto.getProvince() );
		proveedoresEntity.setCp( providerDto.getPostalCode() );
		proveedoresEntity.setTelefono( providerDto.getPhone() );
		proveedoresEntity.setMovil( providerDto.getMobile() );
		return proveedoresEntity;
	}

	/**
	 * Map from EventosEntity to EventDto
	 *
	 * @param eventosEntity data
	 * @return EventDto
	 */
	public static EventDto getEventDtoFromEventosEntity( @NonNull EventosEntity eventosEntity ) {
		Log.println( Log.INFO, "Mapper", "getEventDtoFromEventosEntity" );
		Set<ClientDto> clientDtoSet = new HashSet<>();
		Set<ProviderDto> providerDtoSet = new HashSet<>();
		EventDto eventDto = new EventDto();
		eventDto.setId( eventosEntity.getId() );
		eventDto.setEvent( eventosEntity.getNombre() );
		eventDto.setDescription( eventosEntity.getDescripcion() );
		eventDto.setDate( eventosEntity.getFecha() );
		eventDto.setActive( eventosEntity.getActivo() == 1 );

		if ( eventosEntity.getClientes() != null && eventosEntity.getClientes().size() > 0 ) {
			for ( ClientesEntity clientesEntity : eventosEntity.getClientes() ) {
				clientDtoSet.add( getClientDtoFromClientesEntity( clientesEntity ) );
			}
		}

		eventDto.setClients( clientDtoSet );

		if ( eventosEntity.getProveedores() != null && eventosEntity.getProveedores().size() > 0 ) {
			for ( ProveedoresEntity proveedoresEntity : eventosEntity.getProveedores() ) {
				providerDtoSet.add( getProviderDtoFromProveedoresEntity( proveedoresEntity ) );
			}
		}

		eventDto.setProviders( providerDtoSet );
		return eventDto;
	}

	/**
	 * Map from EventDto to EventosEntity
	 *
	 * @param eventDto data
	 * @return EventosEntity
	 */
	public static EventosEntity getEventosEntityFromEventDto( @NonNull EventDto eventDto ) {
		Log.println( Log.INFO, "Mapper", "getEventosEntityFromEventDto" );
		Set<ClientesEntity> clientesEntitySet = new HashSet<>();
		Set<ProveedoresEntity> proveedoresEntitySet = new HashSet<>();
		EventosEntity eventosEntity = new EventosEntity();
		eventosEntity.setId( eventDto.getId() );
		eventosEntity.setNombre( eventDto.getEvent() );
		eventosEntity.setDescripcion( eventDto.getDescription() );
		eventosEntity.setFecha( eventDto.getDate() );
		eventosEntity.setActivo( ( eventDto.getActive() ) ? 1 : 0 );

		if ( eventDto.getClients() != null && eventDto.getClients().size() > 0 ) {
			for ( ClientDto clientDto : eventDto.getClients() ) {
				clientesEntitySet.add( getClientesEntityFromClientDto( clientDto ) );
			}
		}

		eventosEntity.setClientes( clientesEntitySet );
		if ( eventDto.getProviders() != null && eventDto.getProviders().size() > 0 ) {
			for ( ProviderDto providerDto : eventDto.getProviders() ) {
				proveedoresEntitySet.add( getProveedoresEntityFromProviderDto( providerDto ) );
			}
		}

		eventosEntity.setProveedores( proveedoresEntitySet );

		return eventosEntity;
	}

	/**
	 * Map from ServiciosEntity to ServiceDto
	 *
	 * @param serviciosEntity data
	 * @return ServiceDto
	 */
	public static ServiceDto geServiceDtoFromServiciosEntity( @NonNull ServiciosEntity serviciosEntity ) {
		Log.println( Log.INFO, "Mapper", "geServiceDtoFromServiciosEntity" );
		ServiceDto serviceDto = new ServiceDto();
		serviceDto.setId( serviciosEntity.getId() );
		serviceDto.setService( serviciosEntity.getNombre() );
		return serviceDto;
	}

	/**
	 * Map from ServiceDto to ServiciosEntity
	 *
	 * @param serviceDto data
	 * @return ServiciosEntity
	 */
	public static ServiciosEntity getServiciosEntityFromServiceDto( @NonNull ServiceDto serviceDto ) {
		Log.println( Log.INFO, "Mapper", "getServiciosEntityFromServiceDto" );
		ServiciosEntity serviciosEntity = new ServiciosEntity();
		serviciosEntity.setId( serviceDto.getId() );
		serviciosEntity.setNombre( serviceDto.getService() );
		return serviciosEntity;
	}

	/**
	 * Map from ChatsEntity to ChatDto
	 *
	 * @param chatsEntity data
	 * @return ChatDto
	 */
	public static ChatDto getChatDtoFromChatsEntity( @NonNull ChatsEntity chatsEntity ) {
		Log.println( Log.INFO, "Mapper", "geServiceDtoFromServiciosEntity" );
		ChatDto chatDto = new ChatDto();
		chatDto.setId( chatsEntity.getId() );
		chatDto.setEvent( getEventDtoFromEventosEntity( chatsEntity.getEvento() ) );
		chatDto.setProvider( getProviderDtoFromProveedoresEntity( chatsEntity.getProveedor() ) );
		return chatDto;
	}

	/**
	 * Map from ChatDto to ChatsEntity
	 *
	 * @param chatDto data
	 * @return ChatsEntity
	 */
	public static ChatsEntity getChatsEntityFromChatDto( @NonNull ChatDto chatDto ) {
		Log.println( Log.INFO, "Mapper", "getChatsEntityFromChatDto" );
		ChatsEntity chatsEntity = new ChatsEntity();
		chatsEntity.setId( chatDto.getId() );
		chatsEntity.setEvento( getEventosEntityFromEventDto( chatDto.getEvent() ) );
		chatsEntity.setProveedor( getProveedoresEntityFromProviderDto( chatDto.getProvider() ) );
		return chatsEntity;
	}

	/**
	 * Map from MensajesEntity to MessageDto
	 *
	 * @param mensajesEntity data
	 * @return MessageDto
	 */
	public static MessageDto getMessageDtoFromMensajesEntity( @NonNull MensajesEntity mensajesEntity ) {
		Log.println( Log.INFO, "Mapper", "getMessageDtoFromMensajesEntity" );
		MessageDto messageDto = new MessageDto();
		messageDto.setId( mensajesEntity.getId() );
		messageDto.setMessage( mensajesEntity.getMensaje() );
		messageDto.setDate( mensajesEntity.getFecha() );
		messageDto.setOwner( mensajesEntity.getPropietario() );
		if ( mensajesEntity.getCliente() != null ) {
			messageDto.setClient( getClientDtoFromClientesEntity( mensajesEntity.getCliente() ) );
		}
		if ( mensajesEntity.getProveedor() != null ) {
			messageDto.setProvider( getProviderDtoFromProveedoresEntity( mensajesEntity.getProveedor() ) );
		}
		return messageDto;
	}

	/**
	 * Map from Set<MensajesEntity> to Set<MessageDto>
	 *
	 * @param mensajesEntitySet data
	 * @return Set<MessageDto>
	 */
	public static Set<MessageDto> getSetMessageDtoFromSetMensajesEntity( @NonNull Set<MensajesEntity> mensajesEntitySet ) {
		Log.println( Log.INFO, "Mapper", "getSetMessageDtoFromSetMensajesEntity" );
		Set<MessageDto> messageDtoSet = new HashSet<>();
		for ( MensajesEntity mensajesEntity : mensajesEntitySet ) {
			messageDtoSet.add( getMessageDtoFromMensajesEntity( mensajesEntity ) );
		}
		return messageDtoSet;
	}

	/**
	 * Map from MessageDto to MensajesEntity
	 *
	 * @param messageDto data
	 * @return MensajesEntity
	 */
	public static MensajesEntity getMensajesEntityFromMessageDto( @NonNull MessageDto messageDto ) {
		Log.println( Log.INFO, "Mapper", "getMensajesEntityFromMessageDto" );
		MensajesEntity mensajesEntity = new MensajesEntity();
		mensajesEntity.setId( messageDto.getId() );
		mensajesEntity.setMensaje( messageDto.getMessage() );
		mensajesEntity.setFecha( messageDto.getDate() );
		mensajesEntity.setPropietario( messageDto.getOwner() );
		mensajesEntity.setEvento( getEventosEntityFromEventDto( messageDto.getEvent() ) );
		if ( messageDto.getClient() != null ) {
			mensajesEntity.setCliente( getClientesEntityFromClientDto( messageDto.getClient() ) );
		}
		if ( messageDto.getProvider() != null ) {
			mensajesEntity.setProveedor( getProveedoresEntityFromProviderDto( messageDto.getProvider() ) );
		}
		return mensajesEntity;
	}

	/**
	 * Map from TodoEntity to TodoDto
	 *
	 * @param todoEntity data
	 * @return TodoDto
	 */
	public static TodoDto getTodoDtoFromTodoEntity( @NonNull TodoEntity todoEntity ) {
		Log.println( Log.INFO, "Mapper", "getTodoDtoFromTodoEntity" );
		TodoDto todoDto = new TodoDto();
		todoDto.setId( todoEntity.getId() );
		todoDto.setTodo( todoEntity.getNombre() );
		todoDto.setDate( todoEntity.getFecha() );
		todoDto.setDone( todoEntity.getRealizada() == 1 );
		return todoDto;
	}

	/**
	 * Map from TodoDto to TodoEntity
	 *
	 * @param todoDto data
	 * @return ChatsEntity
	 */
	public static TodoEntity getTodoEntityFromTodoDto( @NonNull TodoDto todoDto ) {
		Log.println( Log.INFO, "Mapper", "getTodoEntityFromTodoDto" );
		TodoEntity todoEntity = new TodoEntity();
		todoEntity.setId( todoDto.getId() );
		todoEntity.setNombre( todoDto.getTodo() );
		todoEntity.setFecha( todoDto.getDate() );
		todoEntity.setRealizada( todoDto.getDone() ? 1 : 0 );
		todoEntity.setEvento( getEventosEntityFromEventDto( todoDto.getEvent() ) );
		return todoEntity;
	}
}
