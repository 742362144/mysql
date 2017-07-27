package com.mysql.serviceImpl;/**
 * Created by Coder on 2017/7/23.
 */

import com.mysql.dao.PersonDAO;
import com.mysql.srevice.PersonService;

/**
 * @author
 * @create 2017-07-23 13:52
 **/

public class PersonServiceImpl implements PersonService{

    private PersonDAO personDAO = new PersonDAO();

    @Override
    public boolean reduceMoney(Integer pid) {
        personDAO.reduceMoney(pid);
        return true;
    }
}
