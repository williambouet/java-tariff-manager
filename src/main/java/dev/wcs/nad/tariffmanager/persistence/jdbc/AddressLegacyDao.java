package dev.wcs.nad.tariffmanager.persistence.jdbc;

import dev.wcs.nad.tariffmanager.persistence.entity.Address;
import dev.wcs.nad.tariffmanager.persistence.entity.Contact;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class AddressLegacyDao {

    private final DataSource dataSource;

    // DataSource is configured by Spring in application.properties and injected
    // during Context setup.
    public AddressLegacyDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Optional<Address> getByIdJava7Syntax(long id) {

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ADDRESS WHERE ID=?")) {
            stmt.setLong(1, id);

            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    String city = resultSet.getString(1);
                    String postalcode = resultSet.getString(2);
                    String street = resultSet.getString(3);
                    String number = resultSet.getString(4);
                    
                    Address address = new Address();
                    address.setId(id);
                    address.setCity(city);
                    address.setPostalcode(postalcode);
                    address.setStreet(street);
                    address.setNumber(number);

                    return Optional.of(address);
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return Optional.empty();
    }

}
