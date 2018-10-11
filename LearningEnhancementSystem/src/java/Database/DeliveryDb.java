/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Classes.Delivery;
/**
 *
 * @author Vegard
 */
public class DeliveryDb extends Database{
    private static final String SELECT_DELIVERY = "select * from Delivery where (student_id, module_no) = (?,?)";
    public DeliveryDb() {
        init();
    }
    
    public Delivery getDelivery() {
        
        return null;
    }
    
}
