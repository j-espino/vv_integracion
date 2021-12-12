package com.practica.integracion;

import com.practica.integracion.DAO.AuthDAO;
import com.practica.integracion.DAO.GenericDAO;
import com.practica.integracion.DAO.User;
import com.practica.integracion.manager.SystemManager;

import com.practica.integracion.manager.SystemManagerException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import javax.naming.OperationNotSupportedException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestInvalidUser {

	static User invalidUser = new User("3", "Pepon", "Nieto", "Moratalaz", null);

	@Mock
	static GenericDAO mockGenericDao;
	@Mock
	static AuthDAO mockAuthDao;

	@Test
	public void testDeleteRemoteSystemWithInvalidUser() throws Exception {

		String invalidId = "54321";

		Mockito.lenient().when(mockAuthDao.getAuthData(invalidUser.getId())).thenReturn(null);
		Mockito.lenient().when(mockGenericDao.deleteSomeData(null, invalidId)).thenThrow(new OperationNotSupportedException());

		InOrder ordered = inOrder(mockAuthDao, mockGenericDao);

		SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);
		assertThrows(SystemManagerException.class, () -> {
			manager.deleteRemoteSystem(invalidUser.getId(), invalidId);
		});

		ordered.verify(mockAuthDao).getAuthData(invalidUser.getId());
		ordered.verify(mockGenericDao).deleteSomeData(null, invalidId);

	}

	@Test
	public void testAddRemoteSystemWithInvalidUser() throws OperationNotSupportedException {

		Object data = new Object();

		Mockito.lenient().when(mockAuthDao.getAuthData(invalidUser.getId())).thenReturn(null);
		Mockito.lenient().when(mockGenericDao.updateSomeData(null, data)).thenThrow(new OperationNotSupportedException());

		InOrder ordered = inOrder(mockAuthDao, mockGenericDao);

		SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);
		assertThrows(SystemManagerException.class, () -> {
			manager.addRemoteSystem(invalidUser.getId(), data);
		});

		ordered.verify(mockAuthDao).getAuthData(invalidUser.getId());
		ordered.verify(mockGenericDao).updateSomeData(null, data);
	}

	@Test
	public void stopRemoteSystemWithInvalidUser() throws OperationNotSupportedException {

		String remoteId = "12345";

		Mockito.lenient().when(mockAuthDao.getAuthData(invalidUser.getId())).thenReturn(null);
		Mockito.lenient().when(mockGenericDao.getSomeData(null, "where id=" + remoteId)).thenThrow(new OperationNotSupportedException());

		InOrder ordered = inOrder(mockAuthDao, mockGenericDao);

		SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);
		assertThrows(SystemManagerException.class, () -> {
			manager.stopRemoteSystem(invalidUser.getId(), remoteId);
		});

		ordered.verify(mockAuthDao).getAuthData(invalidUser.getId());
		ordered.verify(mockGenericDao).getSomeData(null, "where id=" + remoteId);
	}


	@Test
	public void startRemoteSystemWithInvalidUser() throws OperationNotSupportedException {

		String remoteId = "12345";

		Mockito.lenient().when(mockAuthDao.getAuthData(invalidUser.getId())).thenReturn(null);
		Mockito.lenient().when(mockGenericDao.getSomeData(null, "where id=" + remoteId)).thenThrow(new OperationNotSupportedException());

		InOrder ordered = inOrder(mockAuthDao, mockGenericDao);

		SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);
		assertThrows(SystemManagerException.class, () -> {
			manager.startRemoteSystem(invalidUser.getId(), remoteId);
		});

		ordered.verify(mockAuthDao).getAuthData(invalidUser.getId());
		ordered.verify(mockGenericDao).getSomeData(null, "where id=" + remoteId);
	}
}




