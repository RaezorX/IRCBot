package net.raezorx.exceptions;

/**
 * User: Raezor
 * Date: 4/12/2014
 * Time: 6:14 PM
 */
public class ConnectionFailedException extends Exception {
    public ConnectionFailedException(String error) {
        super(error);
    }
}
