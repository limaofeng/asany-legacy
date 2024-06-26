package cn.asany.email.client.smtp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.net.SocketFactory;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

/** a channel holds all lower level connection resources */
public interface Channel {

  @Nonnull
  Socket socket();

  @Nonnull
  BufferedSink sink();

  @Nonnull
  BufferedSource source();

  @Nonnull
  InetAddress address();

  ChannelConnector DIRECT =
      (client, address) -> {
        final Socket socket = client.socketFactory().createSocket();
        socket.connect(
            new InetSocketAddress(address, client.defaultPort()), (int) client.connectTimeout());

        final BufferedSink sink = Okio.buffer(Okio.sink(socket));
        final BufferedSource source = Okio.buffer(Okio.source(socket));
        sink.timeout().timeout(client.writeTimeout(), TimeUnit.MILLISECONDS);
        source.timeout().timeout(client.readTimeout(), TimeUnit.MILLISECONDS);

        return new Channel() {
          @Nonnull
          @Override
          public Socket socket() {
            return socket;
          }

          @Nonnull
          @Override
          public BufferedSink sink() {
            return sink;
          }

          @Nonnull
          @Override
          public BufferedSource source() {
            return source;
          }

          @Nonnull
          @Override
          public InetAddress address() {
            return address;
          }
        };
      };

  /**
   * a {@link ChannelConnector} factory take responsibility to open TCP streams and bind all
   * resources to an {@link Channel} channel
   */
  interface ChannelConnector {
    /**
     * connect to the target tcp endpoint using the {@link SocketFactory} socketFactory
     *
     * @param address target address to connect
     * @return a channel which hold connection
     * @throws IOException exception threw when connect failed
     */
    Channel connect(SmtpClient client, InetAddress address) throws IOException;
  }
}
