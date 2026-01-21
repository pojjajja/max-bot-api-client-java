package ru.max.botapi.queries.upload;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.Future;

import org.jetbrains.annotations.Nullable;
import ru.max.botapi.client.ClientResponse;
import ru.max.botapi.client.MaxTransportClient;
import ru.max.botapi.exceptions.ClientException;
import ru.max.botapi.exceptions.TransportClientException;

class StreamUploadExec implements UploadExec {
    private final String fileName;
    private final InputStream input;
    private final String url;
    private final Map<String, String> headers;

    StreamUploadExec(String url, String fileName, InputStream input, @Nullable Map<String, String> headers) {
        this.url = url;
        this.fileName = fileName;
        this.input = input;
        this.headers = headers;
    }

    @Override
    public Future<ClientResponse> newCall(MaxTransportClient transportClient) throws ClientException {
        try {
            return transportClient.post(url, fileName, input, headers);
        } catch (TransportClientException e) {
            throw new ClientException(e);
        }
    }
}
