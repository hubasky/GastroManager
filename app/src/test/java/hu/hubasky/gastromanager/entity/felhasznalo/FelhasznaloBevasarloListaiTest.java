//package hu.hubasky.gastromanager.entity.felhasznalo;
//
//import org.junit.Test;
//
//import hu.hubasky.gastromanager.entity.bevlist.BevasarloLista;
//
//import static org.junit.Assert.*;
//
///**
// * Created by mirso on 2017. 04. 29..
// */
//public class FelhasznaloBevasarloListaiTest {
//    private final Felhasznalo f = new Felhasznalo("1234567", "1234567", "aaaa aaa");
//    private final Felhasznalo ft = new Felhasznalo("1234567t", "1234567t", "aaaa aaat");
//
//
//    @Test
//    public void addLista() throws Exception {
//        FelhasznaloBevasarloListai fb = new FelhasznaloBevasarloListai(f);
//
//        try {
//            fb.addLista(null);
//            fail();
//        } catch (IllegalArgumentException ex) {
//        }
//
//        BevasarloLista b = new BevasarloLista("TODO");
//
//        fb.addLista(b);
//        assertTrue(fb.getListak().contains(b));
//
//        // duplikáció
//        fb.addLista(b);
//        assertTrue(fb.getListak().contains(b));
//        assertEquals(1, fb.getListak().size());
//
//    }
//
//    @Test
//    public void getListak() throws Exception {
//        FelhasznaloBevasarloListai fb = new FelhasznaloBevasarloListai(f);
//        BevasarloLista b = new BevasarloLista("TODO");
//
//        fb.addLista(b);
//        assertTrue(fb.getListak().contains(b));
//
//        try {
//            fb.getListak().add(b);
//            fail();
//        } catch (UnsupportedOperationException ex) {
//
//        }
//    }
//
//}