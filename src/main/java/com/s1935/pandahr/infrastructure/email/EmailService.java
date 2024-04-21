package com.s1935.pandahr.infrastructure.email;

import com.azure.communication.email.EmailClient;
import com.azure.communication.email.EmailClientBuilder;
import com.azure.communication.email.models.EmailMessage;
import com.azure.communication.email.models.EmailSendResult;
import com.azure.communication.email.models.EmailSendStatus;
import com.azure.core.util.polling.LongRunningOperationStatus;
import com.azure.core.util.polling.PollResponse;
import com.azure.core.util.polling.SyncPoller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Slf4j
@Service
public class EmailService
{
    @Value(value = "${email.senderAddress}")
    private String senderAddress;
    @Value(value = "${email.recipientAddress}")
    private String recipientAddress;
    @Value(value = "${email.connectionString}")
    private String connectionString;

    public static final Duration POLLER_WAIT_TIME = Duration.ofSeconds(10);

    public void sendEmail() {
        EmailClient emailClient = new EmailClientBuilder()
                .connectionString(connectionString)
                .buildClient();

        EmailMessage message = buildMessage();

        try {
            SyncPoller<EmailSendResult, EmailSendResult> poller = emailClient.beginSend(message, null);

            PollResponse<EmailSendResult> pollResponse = null;

            Duration timeElapsed = Duration.ofSeconds(0);

            while (isPollingInProgress(pollResponse)) {
                pollResponse = poller.poll();

                Thread.sleep(POLLER_WAIT_TIME.toMillis());
                timeElapsed = timeElapsed.plus(POLLER_WAIT_TIME);

                if (timeElapsed.compareTo(POLLER_WAIT_TIME.multipliedBy(18)) >= 0) {
                    throw new RuntimeException("Polling timed out.");
                }
            }

            if (poller.getFinalResult().getStatus() == EmailSendStatus.SUCCEEDED) {
                log.info("Successfully sent the email to: {} from: {} (operation id: {})", recipientAddress, senderAddress, poller.getFinalResult().getId());
            }
            else {
                throw new RuntimeException(poller.getFinalResult().getError().getMessage());
            }
        }
        catch (Exception e) {
            log.error("An error occurred while sending the email.", e);
        }
    }

    private boolean isPollingInProgress(PollResponse<EmailSendResult> pollResponse) {
        return pollResponse == null
                || pollResponse.getStatus() == LongRunningOperationStatus.NOT_STARTED
                || pollResponse.getStatus() == LongRunningOperationStatus.IN_PROGRESS;
    }

    private EmailMessage buildMessage() {
        return new EmailMessage()
                .setSenderAddress(senderAddress)
                .setToRecipients(recipientAddress)
                .setSubject("Test email from Java Sample")
                .setBodyPlainText("This is plaintext body of test email.")
                .setBodyHtml("<html><h1>This is the html body of test email.</h1></html>");
    }
}