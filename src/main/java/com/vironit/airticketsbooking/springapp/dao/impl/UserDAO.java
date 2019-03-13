package com.vironit.airticketsbooking.springapp.dao.impl;

import com.vironit.airticketsbooking.springapp.dao.EntityDAOImpl;
import com.vironit.airticketsbooking.springapp.entity.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class UserDAO extends EntityDAOImpl<Long, User> {
    private static final String FIND_USER_BY_EMAIL_HQL = "SELECT u FROM User u WHERE u.email = :emailValue";
    private static final String FIND_USER_BY_PASSPORT_NUMBER_HQL = "SELECT u FROM User u WHERE u.passport_number = :passportNumberValue";
    private static final String FIND_USER_BY_PHONE_NUMBER_HQL = "SELECT u FROM User u WHERE u.phone_number = :phoneNumberValue";
    private static final String FIND_USER_BY_EMAIL_AND_PASSWORD_HQL = "FROM User WHERE email = :emailValue AND password = :passwordValue";
    private static final String FIND_USERS_WITH_ORDER_SQL = "SELECT id, email, password, name, surname, passport_number, dob, sex, phone_number, role, BALANCE FROM air_ticket_booking_system.users JOIN air_ticket_booking_system.orders ON orders.id_user = users.id GROUP BY users.id";
    private static final String UPDATE_USER_PROFILE_HQL = "UPDATE User SET password = :passwordValue, name = :nameValue, surname = :surnameValue, dob = :dobValue WHERE id = :idValue";
    private static final String UPDATE_USER_BALANCE_HQL = "UPDATE User SET balance = :balanceValue WHERE id = :idValue";

    public UserDAO() {
        super();
    }

    public User findUserByEmail(String email) {
        Query<User> query = getCurrentSession().createQuery(FIND_USER_BY_EMAIL_HQL);
        query.setParameter("emailValue", email);
        return query.getResultList().stream().findAny().orElse(null);
    }

    public User findUserByPassportNumber(String passport_number) {
        Query<User> query = getCurrentSession().createQuery(FIND_USER_BY_PASSPORT_NUMBER_HQL);
        query.setParameter("passportNumberValue", passport_number);
        return query.getResultList().stream().findAny().orElse(null);
    }

    public User findUserByPhoneNumber(String phone_number) {
        Query<User> query = getCurrentSession().createQuery(FIND_USER_BY_PHONE_NUMBER_HQL);
        query.setParameter("phoneNumberValue", phone_number);
        return query.getResultList().stream().findAny().orElse(null);
    }

    public User findUserByEmailAndPassword(String email, String password) {

        Query<User> query = getCurrentSession().createQuery(FIND_USER_BY_EMAIL_AND_PASSWORD_HQL);
        query.setParameter("emailValue", email);
        query.setParameter("passwordValue", password);
        return query.getSingleResult();
    }

    public List<User> findUsersWithOrder() {
        Query<User> query = getCurrentSession().createNativeQuery(FIND_USERS_WITH_ORDER_SQL);
        return query.list();
    }

    public void updateUserProfile(long id, String password, String name, String surname, Date dob) {
        Query query = getCurrentSession().createQuery(UPDATE_USER_PROFILE_HQL);
        query.setParameter("idValue", id);
        query.setParameter("passwordValue", password);
        query.setParameter("nameValue", name);
        query.setParameter("surnameValue", surname);
        query.setParameter("dobValue", dob);
        query.executeUpdate();
    }

    public void updateBalance(User user, double balance) {
        Query query = getCurrentSession().createQuery(UPDATE_USER_BALANCE_HQL);
        query.setParameter("idValue", user.getId());
        query.setParameter("balanceValue", balance);
        query.executeUpdate();
    }

}
