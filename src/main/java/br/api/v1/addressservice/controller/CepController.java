package br.api.v1.addressservice.controller;

import br.api.v1.addressservice.model.dto.CepResponse;
import br.api.v1.addressservice.service.CepService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller(value = "/api/v1/cep")
public class CepController {

    private final CepService cepService;

    @Inject
    public CepController(@Singleton CepService cepService) {
        this.cepService = cepService;
    }

    @Get(value = "{cep}")
    public HttpResponse<CepResponse> searchCep(@PathVariable String cep){
        return HttpResponse.ok(this.cepService.searchCep(cep));
    }

    @Get(value = "pdf")
    public byte[] generatePdf() throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            contentStream.setFont(PDType1Font.COURIER, 12);
            contentStream.beginText();
            contentStream.showText("Hello World");
            contentStream.endText();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document.save(byteArrayOutputStream);
        document.close();

        return byteArrayOutputStream.toByteArray();
    }
}
