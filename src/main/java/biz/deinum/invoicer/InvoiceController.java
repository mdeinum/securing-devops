package biz.deinum.invoicer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    private final InvoiceRepository repository;

    public InvoiceController(InvoiceRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<Invoice> find(@PathVariable long id) {
        var invoice = repository.findById(id);
        return invoice.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Invoice invoice, UriComponentsBuilder builder) {
        var storedInvoice = repository.save(invoice);
        builder.pathSegment("invoice", "{id}");
        return ResponseEntity.created(builder.buildAndExpand(storedInvoice.getId()).toUri()).build();
    }

    @PutMapping("/{id:[0-9]+}")
    public ResponseEntity<Void> update(@RequestBody Invoice invoice) {
        repository.save(invoice);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id:[0-9]+}")
    public ResponseEntity<Void> remove(@PathVariable long id) {
        repository.deleteById(id);
        return ResponseEntity.accepted().build();
    }
}
