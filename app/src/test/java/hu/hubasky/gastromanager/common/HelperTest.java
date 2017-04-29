package hu.hubasky.gastromanager.common;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import hu.hubasky.gastromanager.entity.Cimke;
import hu.hubasky.gastromanager.entity.ECimkeTipus;

import static org.junit.Assert.*;

/**
 * Created by mirso on 2017. 04. 29..
 */
public class HelperTest {
    @Test
    public void find() throws Exception {
        fail("Nincs megírva!");
    }

    @Test
    public void filter() throws Exception {
        Set<Cimke> cimkek = new HashSet<>();
        Set<Cimke> filter;

        Cimke calap1, calap2, crecept, cminden;

        // csak két alapanyag van benne
        cimkek.add(calap1 = new Cimke(ECimkeTipus.ALAPANYAG, "alap1", "x"));
        cimkek.add(calap2 = new Cimke(ECimkeTipus.ALAPANYAG, "alap2", "x"));

        filter = Helper.filter(cimkek, ECimkeTipus.ALAPANYAG);
        assertTrue(filter.contains(calap1));
        assertTrue(filter.contains(calap2));

        filter = Helper.filter(cimkek, ECimkeTipus.RECEPT);
        assertTrue(filter.isEmpty());

        filter = Helper.filter(cimkek, ECimkeTipus.MINDEN);
        assertTrue(filter.contains(calap1));
        assertTrue(filter.contains(calap2));

        // bekerül egy receptcimke is.
        cimkek.add(crecept = new Cimke(ECimkeTipus.RECEPT, "recept", "x"));

        filter = Helper.filter(cimkek, ECimkeTipus.ALAPANYAG);
        assertTrue(filter.contains(calap1));
        assertTrue(filter.contains(calap2));
        assertFalse(filter.contains(crecept));

        filter = Helper.filter(cimkek, ECimkeTipus.RECEPT);
        assertTrue(filter.contains(crecept));
        assertFalse(filter.contains(calap1));
        assertFalse(filter.contains(calap2));

        filter = Helper.filter(cimkek, ECimkeTipus.MINDEN);
        assertTrue(filter.contains(calap1));
        assertTrue(filter.contains(calap2));
        assertTrue(filter.contains(crecept));

        // bekerül egy mindenhez passzoló cimke is
        cimkek.add(cminden = new Cimke(ECimkeTipus.MINDEN, "minde", "x"));

        filter = Helper.filter(cimkek, ECimkeTipus.ALAPANYAG);
        assertTrue(filter.contains(calap1));
        assertTrue(filter.contains(calap2));
        assertFalse(filter.contains(crecept));
        assertTrue(filter.contains(cminden));

        filter = Helper.filter(cimkek, ECimkeTipus.RECEPT);
        assertFalse(filter.contains(calap1));
        assertFalse(filter.contains(calap2));
        assertTrue(filter.contains(crecept));
        assertTrue(filter.contains(cminden));

        filter = Helper.filter(cimkek, ECimkeTipus.MINDEN);
        assertTrue(filter.contains(calap1));
        assertTrue(filter.contains(calap2));
        assertTrue(filter.contains(crecept));
        assertTrue(filter.contains(cminden));

    }

    @Test
    public void filter1() throws Exception {
        List<Cimke> cimkek = new ArrayList<>();
        List<Cimke> filter;

        Cimke calap1, calap2, crecept, cminden;

        // csak két alapanyag van benne
        cimkek.add(calap1 = new Cimke(ECimkeTipus.ALAPANYAG, "alap1", "x"));
        cimkek.add(calap2 = new Cimke(ECimkeTipus.ALAPANYAG, "alap2", "x"));

        filter = Helper.filter(cimkek, ECimkeTipus.ALAPANYAG);
        assertTrue(filter.contains(calap1));
        assertTrue(filter.contains(calap2));

        filter = Helper.filter(cimkek, ECimkeTipus.RECEPT);
        assertTrue(filter.isEmpty());

        filter = Helper.filter(cimkek, ECimkeTipus.MINDEN);
        assertTrue(filter.contains(calap1));
        assertTrue(filter.contains(calap2));

        // bekerül egy receptcimke is.
        cimkek.add(crecept = new Cimke(ECimkeTipus.RECEPT, "recept", "x"));

        filter = Helper.filter(cimkek, ECimkeTipus.ALAPANYAG);
        assertTrue(filter.contains(calap1));
        assertTrue(filter.contains(calap2));
        assertFalse(filter.contains(crecept));

        filter = Helper.filter(cimkek, ECimkeTipus.RECEPT);
        assertTrue(filter.contains(crecept));
        assertFalse(filter.contains(calap1));
        assertFalse(filter.contains(calap2));

        filter = Helper.filter(cimkek, ECimkeTipus.MINDEN);
        assertTrue(filter.contains(calap1));
        assertTrue(filter.contains(calap2));
        assertTrue(filter.contains(crecept));

        // bekerül egy mindenhez passzoló cimke is
        cimkek.add(cminden = new Cimke(ECimkeTipus.MINDEN, "minde", "x"));

        filter = Helper.filter(cimkek, ECimkeTipus.ALAPANYAG);
        assertTrue(filter.contains(calap1));
        assertTrue(filter.contains(calap2));
        assertFalse(filter.contains(crecept));
        assertTrue(filter.contains(cminden));

        filter = Helper.filter(cimkek, ECimkeTipus.RECEPT);
        assertFalse(filter.contains(calap1));
        assertFalse(filter.contains(calap2));
        assertTrue(filter.contains(crecept));
        assertTrue(filter.contains(cminden));

        filter = Helper.filter(cimkek, ECimkeTipus.MINDEN);
        assertTrue(filter.contains(calap1));
        assertTrue(filter.contains(calap2));
        assertTrue(filter.contains(crecept));
        assertTrue(filter.contains(cminden));
    }

    @Test
    public void isExistsIntersect() throws Exception {
        Set<Integer> s1 = new HashSet<>();
        Set<Integer> s2 = new HashSet<>();

        // üresek, nincs közös elem
        assertFalse(Helper.isExistsIntersect(s1, s2));

        // az elsőben van csak elem.
        s1.add(1);
        assertFalse(Helper.isExistsIntersect(s1, s2));

        // a másodikban van csak elem.
        s1.clear();
        s2.add(1);
        assertFalse(Helper.isExistsIntersect(s1, s2));

        // mindkettőben van, de eltérnek
        s1.clear();
        s1.add(1);
        s2.clear();
        s2.add(2);
        assertFalse(Helper.isExistsIntersect(s1, s2));

        // van közös
        s1.clear();
        s1.add(1);
        s2.clear();
        s2.add(1);
        assertTrue(Helper.isExistsIntersect(s1, s2));
    }

    @Test
    public void trim() throws Exception {
        String res;
        String exp;
        String val;

        val = null;
        exp = "";
        res = Helper.trim(val);
        assertEquals(exp, res);

        val = "";
        exp = "";
        res = Helper.trim(val);
        assertEquals(exp, res);

        val = "a";
        exp = "a";
        res = Helper.trim(val);
        assertEquals(exp, res);

        val = " a ";
        exp = "a";
        res = Helper.trim(val);
        assertEquals(exp, res);

    }

    @Test
    public void contains() throws Exception {
        String s1, s2;
        boolean res;
        boolean exp;

        s1 = null;
        s2 = null;
        exp = false;
        res = Helper.contains(s1, s2);
        assertEquals(exp, res);

        s1 = "x";
        s2 = null;
        exp = false;
        res = Helper.contains(s1, s2);
        assertEquals(exp, res);

        s1 = null;
        s2 = "x";
        exp = false;
        res = Helper.contains(s1, s2);
        assertEquals(exp, res);

        s1 = "alma körte";
        s2 = "x";
        exp = false;
        res = Helper.contains(s1, s2);
        assertEquals(exp, res);

        s1 = "alma körte";
        s2 = "lm";
        exp = true;
        res = Helper.contains(s1, s2);
        assertEquals(exp, res);

        s1 = "alma körte";
        s2 = "LM";
        exp = true;
        res = Helper.contains(s1, s2);
        assertEquals(exp, res);
    }

    @Test
    public void filter2() throws Exception {
        try {
            Helper.filter((List<Integer>) null, (Helper.Checker<Integer>) null);
            fail();
        } catch (IllegalArgumentException ex) {

        }

        List<Integer> miben;
        List<Integer> res;

        // null, amiben keresünk
        miben = null;
        res = Helper.filter(miben, new Helper.Checker<Integer>() {
            @Override
            public boolean check(Integer param) {
                return param < 5;
            }
        });
        assertTrue(res.isEmpty());

        // üres, amiben keresünk
        miben = new ArrayList<>();
        res = Helper.filter(miben, new Helper.Checker<Integer>() {
            @Override
            public boolean check(Integer param) {
                return param < 5;
            }
        });
        assertTrue(res.isEmpty());

        // nincs benne, olyan, ami jö
        miben = new ArrayList<>(Arrays.asList(6, 7, 8, 9));
        res = Helper.filter(miben, new Helper.Checker<Integer>() {
            @Override
            public boolean check(Integer param) {
                return param < 5;
            }
        });
        assertTrue(res.isEmpty());

        // van benne olyan, amit keresünt
        miben = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 6, 7, 8, 9));
        res = Helper.filter(miben, new Helper.Checker<Integer>() {
            @Override
            public boolean check(Integer param) {
                return param < 5;
            }
        });
        assertEquals(4, res.size());
        assertTrue(res.contains(1));
        assertTrue(res.contains(2));
        assertTrue(res.contains(3));
        assertTrue(res.contains(4));
    }

    @Test
    public void filter3() throws Exception {
        try {
            Helper.filter((Set<Integer>) null, (Helper.Checker<Integer>) null);
            fail();
        } catch (IllegalArgumentException ex) {

        }

        Set<Integer> miben;
        Set<Integer> res;

        // null, amiben keresünk
        miben = null;
        res = Helper.filter(miben, new Helper.Checker<Integer>() {
            @Override
            public boolean check(Integer param) {
                return param < 5;
            }
        });
        assertTrue(res.isEmpty());

        // üres, amiben keresünk
        miben = new HashSet<>();
        res = Helper.filter(miben, new Helper.Checker<Integer>() {
            @Override
            public boolean check(Integer param) {
                return param < 5;
            }
        });
        assertTrue(res.isEmpty());

        // nincs benne, olyan, ami jö
        miben = new HashSet<>(Arrays.asList(6, 7, 8, 9));
        res = Helper.filter(miben, new Helper.Checker<Integer>() {
            @Override
            public boolean check(Integer param) {
                return param < 5;
            }
        });
        assertTrue(res.isEmpty());

        // van benne olyan, amit keresünt
        miben = new HashSet<>(Arrays.asList(1, 2, 3, 4, 6, 7, 8, 9));
        res = Helper.filter(miben, new Helper.Checker<Integer>() {
            @Override
            public boolean check(Integer param) {
                return param < 5;
            }
        });
        assertEquals(4, res.size());
        assertTrue(res.contains(1));
        assertTrue(res.contains(2));
        assertTrue(res.contains(3));
        assertTrue(res.contains(4));
    }

    @Test
    public void isEqualsIgnoreCase() throws Exception {
        String s1;
        String s2;

        boolean exp;
        boolean res;

        // mindkettő null
        s1 = null;
        s2 = null;
        exp = false;
        res = Helper.isEqualsIgnoreCase(s1, s2);
        assertEquals(exp, res);

        // első null
        s1 = null;
        s2 = "x";
        exp = false;
        res = Helper.isEqualsIgnoreCase(s1, s2);
        assertEquals(exp, res);

        // második null
        s1 = "x";
        s2 = null;
        exp = false;
        res = Helper.isEqualsIgnoreCase(s1, s2);
        assertEquals(exp, res);

        // eltér
        s1 = "x";
        s2 = "y";
        exp = false;
        res = Helper.isEqualsIgnoreCase(s1, s2);
        assertEquals(exp, res);

        // egyezik-1
        s1 = "xa";
        s2 = "xa";
        exp = true;
        res = Helper.isEqualsIgnoreCase(s1, s2);
        assertEquals(exp, res);

        // egyezik-2
        s1 = "xA";
        s2 = "xa";
        exp = true;
        res = Helper.isEqualsIgnoreCase(s1, s2);
        assertEquals(exp, res);

        // egyezik-3
        s1 = "xa";
        s2 = "xA";
        exp = true;
        res = Helper.isEqualsIgnoreCase(s1, s2);
        assertEquals(exp, res);
    }

}