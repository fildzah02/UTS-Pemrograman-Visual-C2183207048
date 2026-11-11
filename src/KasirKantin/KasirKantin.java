/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KasirKantin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.NumberFormat;
import java.util.Locale;
/**
 *
 * @author FILDZAH
 */
public class KasirKantin extends JFrame {

    // Deklarasi komponen
    private JTextField txtNama;
    private JTextField txtHarga;
    private JTextField txtJumlah;
    private JTextField txtPembayaran;
    private JTextField txtTotal;
    private JTextField txtKembalian;

    private JButton btnHitung;
    private JButton btnBayar;
    private JButton btnReset;

    private long totalHarga = 0;

    // Format mata uang Rupiah
    private final NumberFormat formatRupiah =
            NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    public KasirKantin() {

        setTitle("Aplikasi Kasir Kantin Kampus");
        setSize(650, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel utama
        JPanel panelUtama = new JPanel(new BorderLayout(15, 15));
        panelUtama.setBackground(new Color(241, 245, 249));
        panelUtama.setBorder(
                BorderFactory.createEmptyBorder(20, 25, 20, 25));

        // Bagian judul
        JLabel lblJudul = new JLabel(
                "KASIR KANTIN KAMPUS", SwingConstants.CENTER);
        lblJudul.setFont(new Font("Arial", Font.BOLD, 25));
        lblJudul.setForeground(Color.WHITE);

        JLabel lblSubjudul = new JLabel(
                "Sistem Transaksi Penjualan",
                SwingConstants.CENTER);
        lblSubjudul.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSubjudul.setForeground(Color.WHITE);

        JPanel panelHeader = new JPanel(new GridLayout(2, 1, 5, 5));
        panelHeader.setBackground(new Color(20, 45, 78));
        panelHeader.setBorder(
                BorderFactory.createEmptyBorder(18, 10, 18, 10));

        panelHeader.add(lblJudul);
        panelHeader.add(lblSubjudul);

        // Panel formulir
        JPanel panelForm = new JPanel(new GridLayout(0, 2, 12, 15));
        panelForm.setBackground(Color.WHITE);
        panelForm.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Data Pembelian"),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        txtNama = new JTextField();
        txtHarga = new JTextField();
        txtJumlah = new JTextField();
        txtPembayaran = new JTextField();

        txtTotal = new JTextField();
        txtKembalian = new JTextField();

        // Hasil tidak dapat diedit
        txtTotal.setEditable(false);
        txtKembalian.setEditable(false);

        txtTotal.setBackground(new Color(232, 241, 252));
        txtKembalian.setBackground(new Color(232, 247, 238));

        // Menambahkan label dan input
        tambahBaris(panelForm, "Nama Makanan / Minuman", txtNama);
        tambahBaris(panelForm, "Harga Satuan (Rp)", txtHarga);
        tambahBaris(panelForm, "Jumlah Pembelian", txtJumlah);
        tambahBaris(panelForm, "Uang Pembayaran (Rp)", txtPembayaran);

        tambahBaris(panelForm, "Total Harga", txtTotal);
        tambahBaris(panelForm, "Uang Kembalian", txtKembalian);

        // Panel tombol
        btnHitung = new JButton("HITUNG");
        btnBayar = new JButton("BAYAR");
        btnReset = new JButton("RESET");

        aturTombol(btnHitung, new Color(36, 99, 166));
        aturTombol(btnBayar, new Color(22, 131, 75));
        aturTombol(btnReset, new Color(100, 116, 139));

        JPanel panelTombol = new JPanel(
                new GridLayout(1, 3, 12, 12));
        panelTombol.setBackground(new Color(241, 245, 249));

        panelTombol.add(btnHitung);
        panelTombol.add(btnBayar);
        panelTombol.add(btnReset);

        // Bagian bawah
        JLabel lblFooter = new JLabel(
                "Aplikasi Kasir Kantin Kampus - Java Swing",
                SwingConstants.CENTER);
        lblFooter.setFont(new Font("Arial", Font.PLAIN, 12));
        lblFooter.setForeground(Color.GRAY);

        JPanel panelBawah = new JPanel(new BorderLayout(10, 10));
        panelBawah.setBackground(new Color(241, 245, 249));
        panelBawah.add(panelTombol, BorderLayout.CENTER);
        panelBawah.add(lblFooter, BorderLayout.SOUTH);

        // Menyusun seluruh panel
        panelUtama.add(panelHeader, BorderLayout.NORTH);
        panelUtama.add(panelForm, BorderLayout.CENTER);
        panelUtama.add(panelBawah, BorderLayout.SOUTH);

        add(panelUtama);

        // Event tombol
        btnHitung.addActionListener(e -> hitungTotal());
        btnBayar.addActionListener(e -> prosesPembayaran());
        btnReset.addActionListener(e -> resetForm());

        // Fokus pertama
        txtNama.requestFocusInWindow();
    }

    // Method untuk menambahkan baris formulir
    private void tambahBaris(JPanel panel,
            String teks, JTextField field) {

        JLabel label = new JLabel(teks);
        label.setFont(new Font("Arial", Font.BOLD, 13));

        field.setFont(new Font("Arial", Font.PLAIN, 14));

        panel.add(label);
        panel.add(field);
    }

    // Mengatur tampilan tombol
    private void aturTombol(JButton tombol, Color warna) {

        tombol.setBackground(warna);
        tombol.setForeground(Color.WHITE);
        tombol.setFont(new Font("Arial", Font.BOLD, 13));
        tombol.setFocusPainted(false);
        tombol.setBorderPainted(false);
        tombol.setOpaque(true);
        tombol.setPreferredSize(new Dimension(120, 42));
    }

    // Mengubah angka menjadi Rupiah
    private String rupiah(long nilai) {
        return formatRupiah.format(nilai);
    }

    // Validasi data pembelian dan perhitungan total
    private boolean hitungTotal() {

        try {

            // Validasi input kosong
            String nama = txtNama.getText().trim();
            String hargaInput = txtHarga.getText().trim();
            String jumlahInput = txtJumlah.getText().trim();

            if (nama.isEmpty() || hargaInput.isEmpty()
                    || jumlahInput.isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Nama barang, harga, dan jumlah wajib diisi!",
                        "Peringatan",
                        JOptionPane.WARNING_MESSAGE);

                return false;
            }

            // Validasi angka
            if (!hargaInput.matches("-?\\d+")
                    || !jumlahInput.matches("-?\\d+")) {

                JOptionPane.showMessageDialog(this,
                        "Harga dan jumlah harus berupa angka!",
                        "Input Tidak Valid",
                        JOptionPane.ERROR_MESSAGE);

                return false;
            }

            long harga = Long.parseLong(hargaInput);
            int jumlah = Integer.parseInt(jumlahInput);

            // Validasi nilai
            if (harga < 0) {
                JOptionPane.showMessageDialog(this,
                        "Harga tidak boleh negatif!");
                return false;
            }

            if (jumlah <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Jumlah pembelian harus lebih dari nol!");
                return false;
            }

            // Menghitung total
            totalHarga = Math.multiplyExact(harga, jumlah);

            txtTotal.setText(rupiah(totalHarga));
            txtKembalian.setText("");

            return true;

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this,
                    "Angka yang dimasukkan terlalu besar atau tidak valid!",
                    "Kesalahan Input",
                    JOptionPane.ERROR_MESSAGE);

            return false;

        } catch (ArithmeticException e) {

            JOptionPane.showMessageDialog(this,
                    "Total harga melebihi batas perhitungan!",
                    "Kesalahan",
                    JOptionPane.ERROR_MESSAGE);

            return false;
        }
    }

    // Method untuk proses pembayaran
    private void prosesPembayaran() {

        // Hitung ulang agar data selalu terbaru
        if (!hitungTotal()) {
            return;
        }

        String pembayaranInput =
                txtPembayaran.getText().trim();

        // Validasi pembayaran kosong
        if (pembayaranInput.isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Uang pembayaran wajib diisi!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);

            txtPembayaran.requestFocus();
            return;
        }

        // Validasi angka pembayaran
        if (!pembayaranInput.matches("\\d+")) {

            JOptionPane.showMessageDialog(this,
                    "Uang pembayaran harus berupa angka!",
                    "Input Tidak Valid",
                    JOptionPane.ERROR_MESSAGE);

            txtPembayaran.requestFocus();
            return;
        }

        try {

            long pembayaran =
                    Long.parseLong(pembayaranInput);

            // Validasi nilai pembayaran
            if (pembayaran < 0) {

                JOptionPane.showMessageDialog(this,
                        "Pembayaran tidak boleh negatif!");

                return;
            }

            // Memeriksa kecukupan pembayaran
            if (pembayaran < totalHarga) {

                long kekurangan =
                        totalHarga - pembayaran;

                txtKembalian.setText(
                        "Kurang " + rupiah(kekurangan));

                JOptionPane.showMessageDialog(this,
                        "Pembayaran kurang sebesar "
                        + rupiah(kekurangan),
                        "Pembayaran Kurang",
                        JOptionPane.WARNING_MESSAGE);

                return;
            }

            // Menghitung kembalian
            long kembalian =
                    pembayaran - totalHarga;

            txtKembalian.setText(rupiah(kembalian));

            JOptionPane.showMessageDialog(this,
                    "Pembayaran Berhasil!\n\n"
                    + "Nama Barang : "
                    + txtNama.getText() + "\n"
                    + "Total Harga : "
                    + rupiah(totalHarga) + "\n"
                    + "Pembayaran  : "
                    + rupiah(pembayaran) + "\n"
                    + "Kembalian   : "
                    + rupiah(kembalian),
                    "Transaksi Berhasil",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this,
                    "Nominal pembayaran terlalu besar atau tidak valid!",
                    "Kesalahan Input",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method reset formulir
    private void resetForm() {

        txtNama.setText("");
        txtHarga.setText("");
        txtJumlah.setText("");
        txtPembayaran.setText("");
        txtTotal.setText("");
        txtKembalian.setText("");

        totalHarga = 0;

        // Fokus kembali ke input pertama
        txtNama.requestFocusInWindow();
    }

    // Method utama
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new KasirKantin().setVisible(true);
        });
    }
}

