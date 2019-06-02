package com.iessanvincente.weddingplanning.helper;

import android.util.Log;

import com.iessanvincente.weddingplanning.domain.ClientDto;
import com.iessanvincente.weddingplanning.domain.EventDto;
import com.iessanvincente.weddingplanning.domain.ProviderDto;
import com.iessanvincente.weddingplanning.entity.ClientesEntity;
import com.iessanvincente.weddingplanning.entity.EventosEntity;
import com.iessanvincente.weddingplanning.entity.ProveedoresEntity;

import java.util.HashSet;
import java.util.Set;


public class MappingHelper {

	/**
	 * Map from ClientesEntity to ClientDto
	 *
	 * @param clientesEntity data
	 * @return ClientDto
	 */
	public static ClientDto getClientDtoFromClientesEntity( ClientesEntity clientesEntity ) {
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
	public static ClientesEntity getClientesEntityFromClientDto( ClientDto clientDto ) {
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
	public static ProviderDto getProviderDtoFromProveedoresEntity( ProveedoresEntity proveedoresEntity ) {
		Log.println( Log.INFO, "Mapper", "getClientDtoFromClientesEntity" );
		ProviderDto providerDto = new ProviderDto();
		providerDto.setId( proveedoresEntity.getId() );
		providerDto.setCif( proveedoresEntity.getCif() );
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
	public static ProveedoresEntity getProveedoresEntityFromProviderDto( ProviderDto providerDto ) {
		Log.println( Log.INFO, "Mapper", "getProveedoresEntityFromProviderDto" );
		ProveedoresEntity proveedoresEntity = new ProveedoresEntity();
		proveedoresEntity.setId( providerDto.getId() );
		proveedoresEntity.setCif( providerDto.getCif() );
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
	public static EventDto getEventDtoFromEventosEntity( EventosEntity eventosEntity ) {
		Log.println( Log.INFO, "Mapper", "getEventDtoFromEventosEntity" );
		Set<ClientDto> clientDtoSet = new HashSet<>();
		Set<ProviderDto> providerDtoSet = new HashSet<>();
		EventDto eventDto = new EventDto();
		eventDto.setId( eventosEntity.getId() );
		eventDto.setEvent( eventosEntity.getNombre() );
		eventDto.setDescription( eventosEntity.getDescripcion() );
		eventDto.setDate( eventosEntity.getFecha() );
		eventDto.setActive( eventosEntity.getActivo() == 1 );

		for ( ClientesEntity clientesEntity : eventosEntity.getClientes() ) {
			clientDtoSet.add( getClientDtoFromClientesEntity( clientesEntity ) );
		}

		eventDto.setClients( clientDtoSet );

		for ( ProveedoresEntity proveedoresEntity : eventosEntity.getProveedores() ) {
			providerDtoSet.add( getProviderDtoFromProveedoresEntity( proveedoresEntity ) );
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
	public static EventosEntity getEventosEntityFromEventDto( EventDto eventDto ) {
		Log.println( Log.INFO, "Mapper", "getEventosEntityFromEventDto" );
		Set<ClientesEntity> clientesEntitySet = new HashSet<>();
		Set<ProveedoresEntity> proveedoresEntitySet = new HashSet<>();
		EventosEntity eventosEntity = new EventosEntity();
		eventosEntity.setId( eventDto.getId() );
		eventosEntity.setNombre( eventDto.getEvent() );
		eventosEntity.setDescripcion( eventDto.getDescription() );
		eventosEntity.setFecha( eventDto.getDate() );
		eventosEntity.setActivo( ( eventDto.getActive() ) ? 1 : 0 );

		for ( ClientDto clientDto : eventDto.getClients() ) {
			clientesEntitySet.add( getClientesEntityFromClientDto( clientDto ) );
		}

		eventosEntity.setClientes( clientesEntitySet );

		for ( ProviderDto providerDto : eventDto.getProviders() ) {
			proveedoresEntitySet.add( getProveedoresEntityFromProviderDto( providerDto ) );
		}

		eventosEntity.setClientes( clientesEntitySet );

		return eventosEntity;
	}
}
