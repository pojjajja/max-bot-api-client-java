package ru.max.botapi.queries.upload;

import java.io.File;
import java.util.Map;
import java.util.concurrent.Future;

import org.jetbrains.annotations.Nullable;
import ru.max.botapi.client.ClientResponse;
import ru.max.botapi.client.MaxTransportClient;
import ru.max.botapi.exceptions.ClientException;
import ru.max.botapi.exceptions.TransportClientException;

class FileUploadExec implements UploadExec {
    private final File file;
    private final String url;
    private final Map<String, String> headers;

    FileUploadExec(String url, File file, @Nullable Map<String, String> headers) {
        this.url = url;
        this.file = file;
        this.headers = headers;
    }

    @Override
    public Future<ClientResponse> newCall(MaxTransportClient transportClient) throws ClientException,
            InterruptedException {

        try {
            return transportClient.post(url, file, headers);
        } catch (TransportClientException e) {
            throw new ClientException(e);
        }
    }
}
