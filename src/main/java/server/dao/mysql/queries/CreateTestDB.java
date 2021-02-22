package server.dao.mysql.queries;

/**
 * Contains string to work with test db in unit tests
 */
public interface CreateTestDB {
    String SQL_CREATE_TEST_DB = "SET FOREIGN_KEY_CHECKS = 0;truncate orderhasticket;truncate `order`;truncate user;truncate notification;truncate ticket;truncate column_has_seat;truncate seat;truncate session;truncate film;truncate director;truncate film_genre;truncate genre;truncate hall;truncate `column`;SET FOREIGN_KEY_CHECKS = 1;";
}
