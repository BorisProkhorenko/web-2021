package com.epam.web.mapper;

import com.epam.web.dao.DaoException;
import com.epam.web.entity.Identifiable;
import com.epam.web.enums.EnumParsingException;
import com.epam.web.enums.Role;


import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T extends Identifiable> {
    T map (ResultSet resultSet) throws SQLException, DaoException;

}
