package fi.uba.parking.persistence.impl;

import org.springframework.stereotype.Repository;

import fi.uba.parking.domain.User;
import fi.uba.parking.persistence.IUserDao;

@Repository
public class UserDaoImpl  extends AbstractDao<User, Long> implements IUserDao{

}
