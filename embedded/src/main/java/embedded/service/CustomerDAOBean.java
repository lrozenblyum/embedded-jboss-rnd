package embedded.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CustomerDAOBean implements CustomerDAOLocal
{
   @PersistenceContext(unitName="custdb")
   private EntityManager em;

   public int createCustomer(String name)
   {
      Customer cust = new Customer();
      cust.setName(name);
      em.persist(cust);
      return cust.getId();
   }

   public Customer findCustomer(int id)
   {
      return em.find(Customer.class, id);
   }
}