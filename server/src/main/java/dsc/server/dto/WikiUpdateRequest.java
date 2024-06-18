package dsc.server.dto;

public record WikiUpdateRequest(
    Long metaId,
    Double ewma,
    int editCount
) {
}
