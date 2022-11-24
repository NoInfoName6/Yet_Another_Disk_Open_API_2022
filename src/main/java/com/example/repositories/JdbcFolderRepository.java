package com.example.repositories;

import com.example.model.SystemItemFolder;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcFolderRepository implements FolderRepository {
    private JdbcOperations jdbcOperations;
    public JdbcFolderRepository(JdbcOperations jdbcOperations){this.jdbcOperations=jdbcOperations;}

    @Override
    public boolean importFolder(SystemItemFolder folder) {
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "INSERT INTO system_item_folder (id, update_date, parent_id) VALUES (?,?,?)",
                Types.VARCHAR, Types.TIMESTAMP_WITH_TIMEZONE, Types.VARCHAR);
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        folder.getId(),
                        folder.getDate(),
                        folder.getParentId()
                ));
        jdbcOperations.update(psc);
        return true;
    }

    @Override
    public boolean deleteFolderById(String id) {
        return false;
    }

    @Override
    public Optional<SystemItemFolder> getFolderById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<SystemItemFolder>> getFoldersByParentId(String parentId) {
        return Optional.empty();
    }
}