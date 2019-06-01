package com.iessanvincente.weddingplanning.helper;

import android.util.Log;

import com.iessanvincente.weddingplanning.domain.ClientDto;
import com.iessanvincente.weddingplanning.entity.ClientesEntity;


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

}
