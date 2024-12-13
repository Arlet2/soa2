package su.arlet.controller;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ConnectionExceptionMapper implements ExceptionMapper<ConnectionException> {
    @Override
    public Response toResponse(ConnectionException e) {
        return Response.status(500).build();
    }
}
