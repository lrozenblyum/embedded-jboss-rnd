package embedded.service;

import javax.ejb.Local;

@Local
public interface CustomerDAOLocal
{
   int createCustomer(String name);

   Customer findCustomer(int id);
}