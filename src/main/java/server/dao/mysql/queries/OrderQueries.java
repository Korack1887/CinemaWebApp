package server.dao.mysql.queries;

/**
 * Store strings which contains queries for SQL to work with order entity
 */
public interface OrderQueries {
    String SQL_ADD_ORDER = "INSERT INTO .`order`\n" +
            "(`id_order`,`order_status`,`id_user`,`date`,`total_price`)\n" +
            "VALUES\n" +
            "(?, ?, ?, ?, ?);";

    String SQL_ADD_TICKET = "INSERT INTO .`ticket`\n" +
            "(`id_ticket`,`id_seat`,`id_session`,`id_column`)\n" +
            "VALUES\n" +
            "(? ,? ,? ,? );";

    String SQL_ADD_TO_CART = "INSERT INTO .`orderhasticket`\n" +
            "(`id_order`,`id_ticket`)\n" +
            "VALUES\n" +
            "(? ,? );";
}
