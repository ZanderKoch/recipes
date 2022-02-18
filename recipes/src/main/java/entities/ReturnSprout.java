
package entities;

import javax.ws.rs.core.Response;

/**
 * Class to be returned by all beans, contains an entity and a status code
 * @author Zander Koch
 */
public class ReturnSprout <T>{
    private T entity;
    private Response.Status status;

    public ReturnSprout(T entity, Response.Status status){
        this.entity = entity;
        this.status = status;
    }
}
