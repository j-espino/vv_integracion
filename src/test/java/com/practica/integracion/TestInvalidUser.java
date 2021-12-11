package com.practica.integracion;

import com.practica.integracion.DAO.AuthDAO;
import com.practica.integracion.DAO.GenericDAO;
import com.practica.integracion.DAO.User;
import com.practica.integracion.manager.SystemManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;



import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestInvalidUser {

	@Test
	public void testDeleteInvalidUser() throws Exception {
		GenericDAO mockGenericDao = mock(GenericDAO.class);
		AuthDAO mockAuthDao = mock(AuthDAO.class);



		User invalidUser = new User("3", "Pepon", "Nieto", "Moratalaz", null);
		String invalidId = "54321";
		Mockito.lenient().when(mockAuthDao.getAuthData(invalidUser.getId())).thenReturn(null);
		Mockito.lenient().when(mockGenericDao.deleteSomeData(invalidUser, invalidId)).thenReturn(true);


		InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
		SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);
		manager.deleteRemoteSystem(invalidUser.getId(), invalidId);


		ordered.verify(mockAuthDao).getAuthData(invalidUser.getId());
		ordered.verify(mockGenericDao).deleteSomeData(invalidUser, invalidId);



	}


}
