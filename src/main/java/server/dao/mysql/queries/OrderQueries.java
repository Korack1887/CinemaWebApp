package server.dao.mysql.queries;

public interface OrderQueries {
    String SQL_ADD_ORDER ="INSERT INTO `cinemaweb`.`order`\n" +
            "(`id_order`,`order_status`,`id_user`,`date`,`total_price`)\n" +
            "VALUES\n" +
            "(?, ?, ?, ?, ?);";

    String SQL_ADD_TICKET = "INSERT INTO `cinemaweb`.`ticket`\n" +
            "(`id_ticket`,`id_seat`,`id_session`,`id_column`)\n" +
            "VALUES\n" +
            "(? ,? ,? ,? );";

    String SQL_ADD_TO_CART = "INSERT INTO `cinemaweb`.`orderhasticket`\n" +
            "(`id_order`,`id_ticket`)\n" +
            "VALUES\n" +
            "(? ,? );";
}
