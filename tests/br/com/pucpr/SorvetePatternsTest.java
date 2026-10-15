package br.com.pucpr;

import br.com.pucpr.adicionais.CaldaChocolate;
import br.com.pucpr.adicionais.CaldaMorango;
import br.com.pucpr.adicionais.Granulado;
import br.com.pucpr.adicionais.Nutella;
import br.com.pucpr.pagamento.Pagamento;
import br.com.pucpr.pagamento.metodos.CartaoCredito;
import br.com.pucpr.pagamento.metodos.CartaoDebito;
import br.com.pucpr.pagamento.metodos.Dinheiro;
import br.com.pucpr.pagamento.metodos.Pix;
import br.com.pucpr.pagamento.metodos.definicaoStrategy.PagamentoStrategy;
import br.com.pucpr.produtos.Sorvete;
import br.com.pucpr.produtos.SorveteComum;
import br.com.pucpr.utilidades.FormatadorBRL;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public final class SSorvetePatternsTest {
    private static final List<String> results = new ArrayList<>();
    private static int failures;

    private SorvetePatternsTest() {}

    @Test
    @Order(1)
    void sp01SorveteComum() throws Exception {
        run("SP-01", "Sorvete comum: descrição e preço", SorvetePatternsTest::plainIceCream);
    }

    @Test
    @Order(2)
    void sp02DataDeCriacao() throws Exception {
        run("SP-02", "Formatação da data de criação", SorvetePatternsTest::creationDate);
    }

    @Test
    @Order(3)
    void sp03CaldaMorango() throws Exception {
        run("SP-03", "Calda de morango", SorvetePatternsTest::strawberryTopping);
    }

    @Test
    @Order(4)
    void sp04CaldaChocolate() throws Exception {
        run("SP-04", "Calda de chocolate", SorvetePatternsTest::chocolateTopping);
    }

    @Test
    @Order(5)
    void sp05Granulado() throws Exception {
        run("SP-05", "Granulado", SorvetePatternsTest::sprinklesTopping);
    }

    @Test
    @Order(6)
    void sp06Nutella() throws Exception {
        run("SP-06", "Nutella", SorvetePatternsTest::nutellaTopping);
    }

    @Test
    @Order(7)
    void sp07AcumuloDeDecorators() throws Exception {
        run("SP-07", "Acúmulo de decorators", SorvetePatternsTest::stackedToppings);
    }

    @Test
    @Order(8)
    void sp08DelegacaoDoDecorator() throws Exception {
        run("SP-08", "Decorator encaminha data e pagamento", SorvetePatternsTest::decoratorDelegation);
    }

    @Test
    @Order(9)
    void sp09CartaoCredito() throws Exception {
        run("SP-09", "Estratégia de cartão de crédito", () -> paymentStrategy(new CartaoCredito(), "cartão de crédito"));
    }

    @Test
    @Order(10)
    void sp10CartaoDebito() throws Exception {
        run("SP-10", "Estratégia de cartão de débito", () -> paymentStrategy(new CartaoDebito(), "cartão de débito"));
    }

    @Test
    @Order(11)
    void sp11Dinheiro() throws Exception {
        run("SP-11", "Estratégia de dinheiro", () -> paymentStrategy(new Dinheiro(), "com dinheiro"));
    }

    @Test
    @Order(12)
    void sp12Pix() throws Exception {
        run("SP-12", "Estratégia de PIX", () -> paymentStrategy(new Pix(), "via PIX"));
    }

    @Test
    @Order(13)
    void sp13EstadoDoPagamento() throws Exception {
        run("SP-13", "Pagamento chama estratégia e atualiza estado", SorvetePatternsTest::paymentState);
    }

    @Test
    @Order(14)
    void sp14FormatadorBrl() throws Exception {
        run("SP-14", "Singleton e formatação pt-BR", SorvetePatternsTest::brazilianCurrency);
    }

    @Test
    @Order(15)
    void sp15EntradasInvalidas() throws Exception {
        run("SP-15", "Entradas inválidas no pagamento", SorvetePatternsTest::invalidPaymentInputs);
    }

    @Test
    @Order(16)
    void sp16FluxoDoMenu() throws Exception {
        run("SP-16", "Fluxo integrado pelo menu", SorvetePatternsTest::menuFlow);
    }

    @AfterAll
    static void saveEvidence() throws Exception {
        Path report = Path.of("target", "test-evidence", "test-results.tsv");
        Files.createDirectories(report.getParent());
        Files.writeString(report, String.join("\n", results) + "\n", StandardCharsets.UTF_8);
        System.out.println("RESULTADO: " + (results.size() - failures) + "/" + results.size() + " aprovados; " + failures + " falhas.");
        System.out.println("EVIDÊNCIAS: " + report.toAbsolutePath());
    }

    private static String plainIceCream() {
        Sorvete sorvete = new SorveteComum();
        equal("Sorvete", sorvete.getDescricao());
        decimal(5.00, sorvete.getPreco());
        return "descrição Sorvete; preço 5,00";
    }

    private static String creationDate() {
        String date = new SorveteComum().getDataHoraCriacao();
        check(date.matches("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}"), "formato de data inesperado: " + date);
        return "data formatada: " + date;
    }

    private static String strawberryTopping() {
        Sorvete sorvete = new CaldaMorango(new SorveteComum());
        check(sorvete.getDescricao().endsWith("calda de morango"), "descrição da calda de morango ausente");
        decimal(5.50, sorvete.getPreco());
        return "calda de morango; preço 5,50";
    }

    private static String chocolateTopping() {
        Sorvete sorvete = new CaldaChocolate(new SorveteComum());
        check(sorvete.getDescricao().endsWith("calda de chocolate"), "descrição da calda de chocolate ausente");
        decimal(5.50, sorvete.getPreco());
        return "calda de chocolate; preço 5,50";
    }

    private static String sprinklesTopping() {
        Sorvete sorvete = new Granulado(new SorveteComum());
        check(sorvete.getDescricao().endsWith("granulado"), "descrição do granulado ausente");
        decimal(5.30, sorvete.getPreco());
        return "granulado; preço 5,30";
    }

    private static String nutellaTopping() {
        Sorvete sorvete = new Nutella(new SorveteComum());
        check(sorvete.getDescricao().endsWith("nutella"), "descrição da Nutella ausente");
        decimal(9.67, sorvete.getPreco());
        return "Nutella; preço 9,67";
    }

    private static String stackedToppings() {
        Sorvete sorvete = new Nutella(new Granulado(new CaldaMorango(new CaldaChocolate(new SorveteComum()))));
        equal("Sorvete, calda de chocolate, calda de morango, granulado, nutella", sorvete.getDescricao());
        decimal(10.97, sorvete.getPreco());
        return "quatro adicionais; preço 10,97; descrição em sequência";
    }

    private static String decoratorDelegation() {
        SorveteComum base = new SorveteComum();
        String date = base.getDataHoraCriacao();
        Pagamento payment = new Pagamento(value -> {});
        Sorvete decorated = new Granulado(base);
        decorated.setPagamento(payment);
        equal(date, decorated.getDataHoraCriacao());
        check(decorated.getPagamento() == payment, "decorator não encaminhou o pagamento");
        return "data preservada; mesmo objeto de pagamento encaminhado";
    }

    private static String paymentStrategy(PagamentoStrategy strategy, String expectedText) {
        String output = capture(() -> strategy.pagar(5.50));
        check(output.contains(expectedText), "saída não contém: " + expectedText);
        check(output.contains("5.5"), "saída não contém o valor informado");
        return oneLine(output);
    }

    private static String paymentState() {
        AtomicInteger calls = new AtomicInteger();
        AtomicReference<Double> received = new AtomicReference<>();
        Pagamento payment = new Pagamento(value -> {
            calls.incrementAndGet();
            received.set(value);
        });
        check(!payment.isPago(), "pagamento novo começou marcado como pago");
        payment.pagar(9.67);
        decimal(9.67, received.get());
        equal(1, calls.get());
        check(payment.isPago(), "pagamento não foi marcado como pago");
        return "estratégia recebeu 9,67 uma vez; estado false → true";
    }

    private static String brazilianCurrency() {
        var first = FormatadorBRL.getInstance();
        var second = FormatadorBRL.getInstance();
        check(first == second, "getInstance não retornou a mesma instância");
        equal("BRL", first.getCurrency().getCurrencyCode());
        String formatted = first.format(10.0);
        String expected = java.text.NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR")).format(10.0);
        equal(expected, formatted);
        return "10,00 formatado com moeda " + first.getCurrency().getCurrencyCode();
    }

    private static String invalidPaymentInputs() throws Exception {
        String input = "1\nnao\n2\nx\n2\n2\n2\n1\n9\n0\n";
        String output = runApplication(input);
        check(output.contains("Número de pedido inválido."), "entrada não numérica não foi tratada");
        check(output.contains("Pedido não encontrado."), "pedido inexistente não foi tratado");
        check(output.contains("Forma de pagamento inválida."), "forma inválida não foi tratada");
        return "confirmadas mensagens: número inválido; pedido não encontrado; forma inválida";
    }

    private static String menuFlow() throws Exception {
        String input = "1\nsim\n9\nsim\n1\nnao\n1\nnao\n3\n2\n1\n4\n2\n2\n3\n2\n1\n3\n0\n";
        String output = runApplication(input);
        check(output.contains("Sorvete, calda de chocolate"), "pedido decorado não foi listado");
        check(output.contains("Pagamento realizado!"), "pagamentos não foram concluídos");
        check(output.contains("Este pedido já foi pago."), "pagamento repetido não foi recusado");
        check(output.contains("1 ") && output.contains("2 "), "os dois pedidos não foram listados");
        return "2 pedidos criados/listados; PIX e dinheiro concluídos; repetição recusada";
    }

    private static String runApplication(String input) throws Exception {
        String javaExecutable = Path.of(System.getProperty("java.home"), "bin", "java").toString();
        String classPath = System.getProperty("java.class.path");
        Process process = new ProcessBuilder(javaExecutable, "-cp", classPath, "br.com.pucpr.Main")
                .redirectErrorStream(true)
                .start();
        try (OutputStream stdin = process.getOutputStream()) {
            stdin.write(input.getBytes(StandardCharsets.UTF_8));
        }
        if (!process.waitFor(Duration.ofSeconds(10).toMillis(), TimeUnit.MILLISECONDS)) {
            process.destroyForcibly();
            throw new AssertionError("aplicação não encerrou no tempo esperado");
        }
        String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        check(process.exitValue() == 0, "aplicação terminou com código " + process.exitValue() + ": " + oneLine(output));
        return output;
    }

    private static String capture(Runnable action) {
        PrintStream original = System.out;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (PrintStream output = new PrintStream(bytes, true, StandardCharsets.UTF_8)) {
            System.setOut(output);
            action.run();
        } finally {
            System.setOut(original);
        }
        return bytes.toString(StandardCharsets.UTF_8).trim();
    }

    private static void run(String id, String title, ThrowingRunnable action) throws Exception {
        try {
            String evidence = action.run();
            String line = id + "\tOK\t" + title + "\t" + oneLine(evidence);
            results.add(line);
            System.out.println("PASS " + line.replace('\t', ' '));
        } catch (Throwable throwable) {
            failures++;
            String evidence = throwable.getClass().getSimpleName() + ": " + throwable.getMessage();
            String line = id + "\tNOK\t" + title + "\t" + oneLine(evidence);
            results.add(line);
            System.err.println("FAIL " + line.replace('\t', ' '));
            if (throwable instanceof Exception exception) {
                throw exception;
            }
            if (throwable instanceof Error error) {
                throw error;
            }
            throw new RuntimeException(throwable);
        }
    }

    private static String oneLine(String value) {
        return value == null ? "" : value.replace('\n', ' ').replace('\r', ' ').replace('\t', ' ').trim();
    }

    private static void decimal(double expected, double actual) {
        check(Math.abs(expected - actual) < 0.000001, "esperado " + expected + ", recebido " + actual);
    }

    private static void equal(Object expected, Object actual) {
        check(expected.equals(actual), "esperado <" + expected + ">, recebido <" + actual + ">");
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    @FunctionalInterface
    private interface ThrowingRunnable {
        String run() throws Exception;
    }
}
