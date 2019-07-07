package com.teamttdvlp.goodthanbefore.schoolsupport.model.chemicalelement.process

import android.app.Application
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.model.chemicalelement.*
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.chemicalelement.IChemicalElement

class ChemicalElementDataSpawner(var context : Application) {

    fun spawnData () : ArrayList<IChemicalElement> {
        var dataList = ArrayList<IChemicalElement>()

        val greenBackground = context.getDrawable(R.drawable.green)
        val yellowBackground = context.getDrawable(R.drawable.yellow)
        val redBackground = context.getDrawable(R.drawable.red)
        val orangeBackground = context.getDrawable(R.drawable.orange)

        //line 1
        dataList.add(CycleAndGroupElement())
        dataList.add(ShortyTitleChemicalElement(R.drawable.title_ia))
        dataList.add(ShortyTitleChemicalElement(R.drawable.title_iia))

        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())

        dataList.add(ShortyTitleChemicalElement(R.drawable.title_iiia))
        dataList.add(ShortyTitleChemicalElement(R.drawable.title_iva))
        dataList.add(ShortyTitleChemicalElement(R.drawable.title_va))
        dataList.add(ShortyTitleChemicalElement(R.drawable.title_via))
        dataList.add(ShortyTitleChemicalElement(R.drawable.title_viia))
        dataList.add(ShortyTitleChemicalElement(R.drawable.title_viiia))

        dataList.add(EmptySpace())


        //line 2
        dataList.add(CycleChemicalElement(R.drawable.chuky_1))
        dataList.add(
            ChemicalElement(
                greenBackground,
                "Hidro",
                "H",
                10,
                10,
                "1.008",
                "1s1",
                "",
                "2.20",
                "-1, 1"
            )
        )
        dataList.add(EmptyChemicalElement())

        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())

        dataList.add(EmptyChemicalElement())
        dataList.add(EmptyChemicalElement())
        dataList.add(EmptyChemicalElement())
        dataList.add(EmptyChemicalElement())
        dataList.add(EmptyChemicalElement())

        dataList.add(
            ChemicalElement(
                greenBackground,
                "Heli",
                "He",
                2,
                10,
                "4.003",
                "1s2",
                "",
                "",
                ""
            )
        )

        dataList.add(EmptySpace())

        //line 3
        dataList.add(CycleChemicalElement(R.drawable.chuky_2))

        dataList.add(
            ChemicalElement(
                greenBackground,
                "Liti",
                "Li",
                3,
                10,
                "6.94",
                "1s2.2s1",
                "",
                "0.98",
                "1"
            )
        )
        dataList.add(
            ChemicalElement(
                greenBackground,
                "Beri",
                "Be",
                4,
                10,
                "9.01",
                "1s2.2s2",
                "",
                "1.57",
                "2"
            )
        )

        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())


        dataList.add(
            ChemicalElement(
                redBackground,
                "Bo",
                "B",
                5,
                10,
                "10.81",
                "1s2.2s2.2p1",
                "",
                "2.04",
                "3"
            )
        ) // Bo, B, 5, 10.81
        dataList.add(
            ChemicalElement(
                redBackground,
                "Cacbon",
                "C",
                6,
                10,
                "12.01",
                "1s2.2s2.2p2",
                "",
                "2.55",
                "-4,-3,-2,-1,0,1,2,3,4"
            )
        ) // Cacbon, C, 6, 12.01
        dataList.add(
            ChemicalElement(
                redBackground,
                "Nitơ",
                "N",
                7,
                10,
                "14.007",
                "1s2.2s2.2p3",
                "",
                "3.04",
                "-3,1,2,3,4,5"
            )
        ) // Nitơ, N, 7, 14.007
        dataList.add(
            ChemicalElement(
                redBackground,
                "Oxi",
                "O",
                8,
                10,
                "15.999",
                "1s2.2s2.2p4",
                "",
                "3.44",
                "-2,-1[-1/2,-1/3,1],2"
            )
        ) // Oxi, O, 8, 15.999
        dataList.add(
            ChemicalElement(
                redBackground,
                "Flo",
                "F",
                9,
                10,
                "18.998",
                "1s2.2s2.2p5",
                "",
                "3.98",
                "-1"
            )
        ) // Flo, F, 9, 18.998
        dataList.add(
            ChemicalElement(
                redBackground,
                "Neon",
                "Ne",
                10,
                10,
                "20.18",
                "1s2.2s2.2p6",
                "",
                "",
                ""
            )
        ) // Neon, Ne, 10, 20.18


        dataList.add(EmptySpace())

        //line 4
        dataList.add(CycleChemicalElement(R.drawable.chuky_3))

        dataList.add(
            ChemicalElement(
                greenBackground,
                "Natri",
                "Na",
                11,
                10,
                "22.989",
                "[Ne] .3s1",
                "",
                "0.93",
                "1"
            )
        )
        dataList.add(
            ChemicalElement(
                greenBackground,
                "Magiê",
                "Mg",
                12,
                10,
                "24.31",
                "[Ne] .3s2",
                "",
                "1.31",
                "2"
            )
        )

        dataList.add(TitleChemicalElement(R.drawable.title_iiib))
        dataList.add(TitleChemicalElement(R.drawable.title_ivb))
        dataList.add(TitleChemicalElement(R.drawable.title_vb))
        dataList.add(TitleChemicalElement(R.drawable.title_vib))
        dataList.add(TitleChemicalElement(R.drawable.title_viib))
        dataList.add(TitleChemicalElement(R.drawable.title_viiib))
        dataList.add(TitleChemicalElement(R.drawable.title_viiib))
        dataList.add(TitleChemicalElement(R.drawable.title_viiib))
        dataList.add(TitleChemicalElement(R.drawable.title_ib))
        dataList.add(TitleChemicalElement(R.drawable.title_iib))

        dataList.add(
            ChemicalElement(
                redBackground,
                "Nhôm",
                "Al",
                13,
                10,
                "26.98",
                "[Ne] .3s2.3p1",
                "",
                "1.61",
                "3"
            )
        ) // Nhôm, Al, 13, 26.98
        dataList.add(
            ChemicalElement(
                redBackground,
                "Silic",
                "Si",
                14,
                10,
                "28.09",
                "[Ne] .3s2.3p2",
                "",
                "1.90",
                "4"
            )
        ) // Silic, Si, 14, 28.09
        dataList.add(
            ChemicalElement(
                redBackground,
                "Photpho",
                "P",
                15,
                10,
                "30.97",
                "[Ne] .3s2.3p3",
                "",
                "2.19",
                "-3,[1],3,[4],5"
            )
        ) // Photpho, P, 15, 30.97
        dataList.add(
            ChemicalElement(
                redBackground,
                "Lưu Huỳnh",
                "S",
                16,
                10,
                "32.08",
                "[Ne] .3s2.3p4",
                "",
                "2.58",
                "-2,-1[1,2],4,6"
            )
        ) // Lưu huỳnh, S, 16, 32.08
        dataList.add(
            ChemicalElement(
                redBackground,
                "Clo",
                "Cl",
                17,
                10,
                "35.45",
                "[Ne] .3s2.3p5",
                "",
                "3.16",
                "-1,1,3[4],5,7"
            )
        ) // Clo, Cl, 17, 35.45
        dataList.add(
            ChemicalElement(
                redBackground,
                "Argon",
                "Ar",
                18,
                10,
                "39.95",
                "[Ne] .3s2.3p6",
                "",
                "",
                ""
            )
        ) // Argon, Ar, 18, 39.95

        dataList.add(EmptySpace())

        //line 5
        dataList.add(CycleChemicalElement(R.drawable.chuky_4))

        dataList.add(
            ChemicalElement(
                greenBackground,
                "Kali",
                "K",
                10,
                10,
                "39.10",
                "[Ar] .4s1",
                "",
                "0.82",
                "1"
            )
        )
        dataList.add(
            ChemicalElement(
                greenBackground,
                "Canxi",
                "Ca",
                20,
                10,
                "40.08",
                "[Ar] .4s2",
                "",
                "1.00",
                "2"
            )
        )

        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Scanđi",
                "Sc",
                21,
                10,
                "44.96",
                "[Ar] .3d1.4s2",
                "",
                "1.36",
                "3"
            )
        ) // Scanđi, Sc, 21, 44.96
        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Titan",
                "Ti",
                22,
                10,
                "47.90",
                "[Ar] .3d2.4s2",
                "",
                "1.54",
                "2,3,4"
            )
        ) // Titan, Ti, 22, 47.90
        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Vanađi",
                "V",
                23,
                10,
                "50.94",
                "[Ar] .3d3.4s2",
                "",
                "1.63",
                "2,[3],4,5"
            )
        ) // Vanađi, V, 23, 50.94
        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Crôm",
                "Cr",
                24,
                10,
                "51.996",
                "[Ar] .3d5.4s1",
                "",
                "1.66",
                "2,3,4,6"
            )
        ) // Crôm, Cr, 24, 51.996
        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Mangan",
                "Mn",
                25,
                10,
                "54.94",
                "[Ar] .3d5.4s2",
                "",
                "1.55",
                "2,3,4,[5],6,7"
            )
        ) // Mangan, Mn, 25, 54.94
        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Sắt",
                "Fe",
                26,
                10,
                "55.85",
                "[Ar] .3d6.4s2",
                "",
                "1.83",
                "2,3,[4,5,6]"
            )
        ) // Sắt, Fe, 26, 55.85
        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Coban",
                "Co",
                27,
                10,
                "58.93",
                "[Ar] .3d7.4s2",
                "",
                "1.88",
                "2,[3],[4]"
            )
        ) // Coban, Co, 27, 58.93
        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Niken",
                "Ni",
                28,
                10,
                "58.71",
                "[Ar] .3d8.4s2",
                "",
                "1.91",
                "2,[3],[4]"
            )
        ) // Niken, Ni, 28, 58.71
        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Đồng",
                "Cu",
                29,
                10,
                "63.54",
                "[Ar] .3d10.4s1",
                "",
                "1.90",
                "1,2"
            )
        ) // Đồng, Cu, 29, 63.54
        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Kẽm",
                "Zn",
                30,
                10,
                "65.41",
                "[Ar] .3d10.4s2",
                "",
                "1.65",
                "2"
            )
        ) // Kẽm, Zn, 30, 65.41

        dataList.add(
            ChemicalElement(
                redBackground,
                "Gali",
                "Ga",
                31,
                10,
                "69.72",
                "[Ar] .3d10.4s2.4p1",
                "",
                "1.81",
                "3"
            )
        ) // Gali, Ga, 31, 69.72
        dataList.add(
            ChemicalElement(
                redBackground,
                "Gecmani",
                "Ge",
                32,
                10,
                "72.64",
                "[Ar] .3d10.4s2.4p2",
                "",
                "2.01",
                "2,4"
            )
        ) // Gecmani, Ge, 32, 72.64
        dataList.add(
            ChemicalElement(
                redBackground,
                "Asen",
                "As",
                33,
                10,
                "74.92",
                "[Ar] .3d10.4s2.4p3",
                "",
                "2.18",
                "-3,3,5"
            )
        ) // Asen, As, 33, 74.92
        dataList.add(
            ChemicalElement(
                redBackground,
                "Selen",
                "Se",
                34,
                10,
                "78.96",
                "[Ar] .3d10.4s2.4p4",
                "",
                "2.55",
                "-2,4,6"
            )
        ) // Selen, Se, 34, 78.96
        dataList.add(
            ChemicalElement(
                redBackground,
                "Brom",
                "Br",
                35,
                10,
                "79.91",
                "[Ar] .3d10.4s2.4p5",
                "",
                "2.96",
                "-1,1,[3],[4],5,7"
            )
        ) // Brom, Br, 35, 79.91
        dataList.add(
            ChemicalElement(
                redBackground,
                "Krypton",
                "Kr",
                36,
                10,
                "83.80",
                "[Ar] .3d10.4s2.4p6",
                "",
                "3.0",
                "2,4"
            )
        ) // Krypton, Kr, 36, 83.80

        dataList.add(EmptySpace())

        //line 6
        dataList.add(CycleChemicalElement(R.drawable.chuky_5))

        dataList.add(
            ChemicalElement(
                greenBackground,
                "Rubiđi",
                "Rb",
                37,
                10,
                "85.47",
                "[Kr] .5s1",
                "",
                "0.82",
                "1"
            )
        )
        dataList.add(
            ChemicalElement(
                greenBackground,
                "Stronti",
                "Sr",
                38,
                10,
                "87.62",
                "[Kr] .5s2",
                "",
                "0.95",
                "2"
            )
        )

        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Ytri",
                "Y",
                39,
                10,
                "88.91",
                "[Kr] .4d1.5s2",
                "",
                "1.22",
                "3"
            )
        ) // Ytri, Y, 39, 88.91
        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Ziriconi",
                "Zr",
                40,
                10,
                "91.22",
                "[Kr] .4d2.5s2",
                "",
                "1.33",
                "[2],[3],4"
            )
        ) // Ziriconi, Zr, 40, 91.22
        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Niobi",
                "Nb",
                41,
                10,
                "92.91",
                "[Kr] .4d4.5s1",
                "",
                "1.6",
                "2,[3],[4],5"
            )
        ) // Niobi, Nb, 41, 92.91
        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Molipden",
                "Mo",
                42,
                10,
                "95.94",
                "[Kr] .4d5.5s1",
                "",
                "2.16",
                "2,3,4,[5],6"
            )
        ) // Molipden, Mo, 42, 95.94
        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Tecneti",
                "Tc",
                43,
                10,
                "99",
                "[Kr] .4d5.5s2",
                "",
                "1.90",
                "3,4,[5],[6],7"
            )
        ) // Tecneti, Tc, 43, 99
        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Ruteni",
                "Ru",
                44,
                10,
                "101.07",
                "[Kr] .4d7.5s1",
                "",
                "2.2",
                "2,3,4,[5],[6],8"
            )
        ) // Ruteni, Ru, 44, 101.07
        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Rođi",
                "Rh",
                45,
                10,
                "102.91",
                "[Kr] .4d8.5s1",
                "",
                "2.28",
                "2,3,4"
            )
        ) // Rođi, Rh, 45, 102.91
        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Palađi",
                "Pd",
                46,
                10,
                "106.4",
                "[Kr] .4d10.5s0",
                "",
                "2.20",
                "2,[3],4"
            )
        ) // Palađi, Pd, 46, 106.4
        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Bạc",
                "Ag",
                47,
                10,
                "107.87",
                "[Kr] .4d10.5s1",
                "",
                "1.93",
                "1,[2]"
            )
        ) // Bạc, Ag, 47, 107.87
        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Cađimi",
                "Cd",
                48,
                10,
                "112.41",
                "[Kr] .4d10.5s2",
                "",
                "1.69",
                "2"
            )
        ) // Cađimi, Cd, 48, 112.41

        dataList.add(
            ChemicalElement(
                redBackground,
                "Inđi",
                "In",
                49,
                10,
                "114.82",
                "[Kr] .4d10.5s2.5p1",
                "",
                "1.78",
                "1,3"
            )
        ) // Inđi, In, 49, 114.82
        dataList.add(
            ChemicalElement(
                redBackground,
                "Thiếc",
                "Sn",
                50,
                10,
                "118.69",
                "[Kr] .4d10.5s2.5p2",
                "",
                "1.96",
                "2,4"
            )
        ) // Thiếc, Sn, 50, 118.69
        dataList.add(
            ChemicalElement(
                redBackground,
                "Antimon [Stibi]",
                "Sb",
                51,
                10,
                "121.75",
                "[Kr] .4d10.5s2.5p3",
                "",
                "2.05",
                "-3,3,[4],5"
            )
        ) // Antimon_[Stibi], Sb, 51, 121.75
        dataList.add(
            ChemicalElement(
                redBackground,
                "Telu",
                "Te",
                52,
                10,
                "127.6",
                "[Kr] .4d10.5s2.5p4",
                "",
                "2.1",
                "-2,[2],4,6"
            )
        ) // Telu, Te, 52, 127.6
        dataList.add(
            ChemicalElement(
                redBackground,
                "Iot",
                "I",
                53,
                10,
                "126.9",
                "[Kr] .4d10.5s2.5p5",
                "",
                "2.66",
                "-1,1,3,5,7"
            )
        ) // Iot, I, 53, 126.9
        dataList.add(
            ChemicalElement(
                redBackground,
                "Xenon",
                "Xe",
                54,
                10,
                "131.3",
                "[Kr] .4d10.5s2.5p6",
                "",
                "2.6",
                "2,4,6"
            )
        ) // Xenon, Xe, 54, 131.3


        dataList.add(EmptySpace())

        //line 7
        dataList.add(CycleChemicalElement(R.drawable.chuky_6))

        dataList.add(
            ChemicalElement(
                greenBackground,
                "Xesi",
                "Cs",
                55,
                10,
                "132.91",
                "[Xe] .6s1",
                "",
                "0.79",
                "1"
            )
        )
        dataList.add(
            ChemicalElement(
                greenBackground,
                "Bari",
                "Ba",
                56,
                10,
                "137.31",
                "[Xe] .6s2",
                "",
                "0.89",
                "2"
            )
        )

        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Lantan",
                "La",
                57,
                10,
                "138.91",
                "[Xe] .5d1.6s2",
                "",
                "1.1",
                "3"
            )
        ) // Lantan, La, 57, 138.91
        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Hafini",
                "Hf",
                72,
                10,
                "178.49",
                "[Xe] .4f14.5d2.6s2",
                "",
                "1.3",
                "[2],[3],4"
            )
        ) // Hafini, Hf, 72, 178.49
        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Tantan",
                "Ta",
                73,
                10,
                "180.95",
                "[Xe] .4f14.5d3.6s2",
                "",
                "1.5",
                "[2],[3],[4], 5"
            )
        ) // Tantan, Ta, 73, 180.95
        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Vonfam",
                "W",
                74,
                10,
                "183.85",
                "[Xe] .4f14.5d4.6s2",
                "",
                "2.36",
                "2,[3],[4],[5],6"
            )
        ) // Vonfam, W, 74, 183.85
        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Reni",
                "Re",
                75,
                10,
                "186.2",
                "[Xe] .4f14.5d5.6s2",
                "",
                "1.9",
                "[2],3,4,[5],[6],7"
            )
        ) // Reni, Re, 75, 186.2
        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Osimi",
                "Os",
                76,
                10,
                "190.2",
                "[Xe] .4f14.5d6.6s2",
                "",
                "2.2",
                "2,3,4,[6],8"
            )
        ) // Osimi, Os, 76, 190.2
        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Iriđi",
                "Ir",
                77,
                10,
                "192.2",
                "[Xe] .4f14.5d7.6s2",
                "",
                "2.20",
                "2,3,4,[6]"
            )
        ) // Iriđi, Ir, 77, 192.2
        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Platin",
                "Pt",
                78,
                10,
                "195.09",
                "[Xe] .4f14.5d9.6s1",
                "",
                "2.28",
                "2,[3],4,[6]"
            )
        ) // Platin, Pt, 78, 195.09
        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Vàng",
                "Au",
                79,
                10,
                "196.97",
                "[Xe] .4f14.5d10.6s1",
                "",
                "2.54",
                "1,3"
            )
        ) // Vàng, Au, 79, 196.97
        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Thủy Ngân",
                "Hg",
                80,
                10,
                "200.59",
                "[Xe] .4f14.5d10.6s2",
                "",
                "2.00",
                "1,2"
            )
        ) // ThủyNgân, Hg, 80, 200.59

        dataList.add(
            ChemicalElement(
                redBackground,
                "Tali",
                "Tl",
                81,
                10,
                "204.37",
                "[Xe] .4f14.5d10.6s2.6p1",
                "",
                "1.62",
                "1,3"
            )
        ) // Tali, Tl, 81, 204.37
        dataList.add(
            ChemicalElement(
                redBackground,
                "Chì",
                "Pb",
                82,
                10,
                "207.2",
                "[Xe] .4f14.5d10.6s2.6p2",
                "",
                "2.33",
                "2,4"
            )
        ) // Chì, Pb, 82, 207.2
        dataList.add(
            ChemicalElement(
                redBackground,
                "Bitmut",
                "Bi",
                83,
                10,
                "208.98",
                "[Xe] .4f14.5d10.6s2.6p3",
                "",
                "2.02",
                "3,5"
            )
        ) // Bitmut, Bi, 83, 208.98
        dataList.add(
            ChemicalElement(
                redBackground,
                "Poloni",
                "Po",
                84,
                10,
                "[209]",
                "[Xe] .4f14.5d10.6s2.6p4",
                "",
                "2.0",
                "-2,2,4,6"
            )
        ) // Poloni, Po, 84, [209]
        dataList.add(
            ChemicalElement(
                redBackground,
                "Atatin",
                "At",
                85,
                10,
                "[210]",
                "[Xe] .4f14.5d10.6s2.6p5",
                "",
                "2.2",
                "-1,1,3,5,7"
            )
        ) // Atatin, At, 85, [210]
        dataList.add(
            ChemicalElement(
                redBackground,
                "Rađon",
                "Rn",
                86,
                10,
                "[222]",
                "[Xe] .4f14.5d10.6s2.6p6",
                "",
                "",
                "[4]"
            )
        ) // Rađon, Rn, 86, [222]

        dataList.add(EmptySpace())

        // Some line
        dataList.add(CycleChemicalElement(R.drawable.chuky_7))

        dataList.add(
            ChemicalElement(
                greenBackground,
                "Franxi",
                "Fr",
                87,
                10,
                "[223]",
                "[Rn] .7s1",
                "",
                "0.7",
                "1"
            )
        )
        dataList.add(
            ChemicalElement(
                greenBackground,
                "Rađi",
                "Ra",
                88,
                10,
                "226.03",
                "[Rn] .7s2",
                "",
                "0.9",
                "2"
            )
        )

        dataList.add(
            ChemicalElement(
                yellowBackground,
                "Actini",
                "Ac",
                89,
                10,
                "[227]",
                "[Rn] .6d1.7s2",
                "",
                "1.1",
                "3"
            )
        ) // Actini, Ac, 89, [227]

        dataList.add(EmptyChemicalElement())

        dataList.add(EmptyChemicalElement())
        dataList.add(EmptyChemicalElement())
        dataList.add(EmptyChemicalElement())
        dataList.add(EmptyChemicalElement())
        dataList.add(EmptyChemicalElement())
        dataList.add(EmptyChemicalElement())
        dataList.add(EmptyChemicalElement())
        dataList.add(EmptyChemicalElement())
        dataList.add(EmptyChemicalElement())
        dataList.add(EmptyChemicalElement())
        dataList.add(EmptyChemicalElement())
        dataList.add(EmptyChemicalElement())
        dataList.add(EmptyChemicalElement())
        dataList.add(EmptyChemicalElement())

        dataList.add(EmptySpace())

        // Line 8
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())

        dataList.add(EmptySpace())

        // Line 9

        dataList.add(EmptySpace())

        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())

        dataList.add(FamilyChemicalElement("Họ Lantan"))

        dataList.add(
            ChemicalElement(
                orangeBackground,
                "Ceri",
                "Ce",
                58,
                10,
                "140.12",
                "[Xe] .4f2.5d0.6s2",
                "",
                "1.12",
                "3,4"
            )
        )
        dataList.add(
            ChemicalElement(
                orangeBackground,
                "Prazeođim",
                "Pr",
                59,
                10,
                "140.91",
                "[Xe] .4f3.5d0.6s2",
                "",
                "1.13",
                "3,4"
            )
        )
        dataList.add(
            ChemicalElement(
                orangeBackground,
                "Neođim",
                "Nd",
                60,
                10,
                "144.24",
                "[Xe] .4f4.5d0.6s2",
                "",
                "1.14",
                "3,4"
            )
        )
        dataList.add(
            ChemicalElement(
                orangeBackground,
                "Prometi",
                "Pm",
                61,
                10,
                "[147]",
                "[Xe] .4f5.5d0.6s2",
                "",
                "1.13",
                "3,4"
            )
        )
        dataList.add(
            ChemicalElement(
                orangeBackground,
                "Samari",
                "Sm",
                62,
                10,
                "150.35",
                "[Xe] .4f6.5d0.6s2",
                "",
                "1.17",
                "3,4"
            )
        )
        dataList.add(
            ChemicalElement(
                orangeBackground,
                "Europi",
                "Eu",
                63,
                10,
                "151.96",
                "[Xe] .4f7.5d0.6s2",
                "",
                "1.2",
                "3,4"
            )
        )
        dataList.add(
            ChemicalElement(
                orangeBackground,
                "Gađoleni",
                "Gd",
                64,
                10,
                "156.25",
                "[Xe] .4f7.5d1.6s2",
                "",
                "1.2",
                "3,4"
            )
        )
        dataList.add(
            ChemicalElement(
                orangeBackground,
                "Tebi",
                "Tb",
                65,
                10,
                "158.93",
                "[Xe] .4f9.5d0.6s2",
                "",
                "1.1",
                "3,4"
            )
        )
        dataList.add(
            ChemicalElement(
                orangeBackground,
                "Điprozi",
                "Dy",
                66,
                10,
                "162.50",
                "[Xe] .4f10.5d0.6s2",
                "",
                "1.22",
                "3,4"
            )
        )
        dataList.add(
            ChemicalElement(
                orangeBackground,
                "Honmi",
                "Ho",
                67,
                10,
                "164.93",
                "[Xe] .4f11.5d0.6s2",
                "",
                "1.23",
                "3,4"
            )
        )
        dataList.add(
            ChemicalElement(
                orangeBackground,
                "Eribi",
                "Er",
                68,
                10,
                "167.26",
                "[Xe] .4f12.5d0.6s2",
                "",
                "1.24",
                "3,4"
            )
        )
        dataList.add(
            ChemicalElement(
                orangeBackground,
                "Tuli",
                "Tm",
                69,
                10,
                "168.93",
                "[Xe] .4f13.5d0.6s2",
                "",
                "1.25",
                "3,4"
            )
        )
        dataList.add(
            ChemicalElement(
                orangeBackground,
                "Ytecbi",
                "Yb",
                70,
                10,
                "173.04",
                "[Xe] .4f14.5d0.6s2",
                "",
                "1.1",
                "3,4"
            )
        )
        dataList.add(
            ChemicalElement(
                orangeBackground,
                "Lutexi ",
                "Lu",
                71,
                10,
                "174.97",
                "[Xe] .4f14.5d1.6s2",
                "",
                "1.27",
                "3,4"
            )
        )

        dataList.add(EmptySpace())

        // Line 10
        dataList.add(EmptySpace())

        dataList.add(EmptySpace())
        dataList.add(EmptySpace())
        dataList.add(EmptySpace())

        dataList.add(FamilyChemicalElement("Họ Actini"))

        dataList.add(
            ChemicalElement(
                orangeBackground,
                "Thori",
                "Th",
                90,
                10,
                "232.04",
                "[Rn] .5f0.6d2.7s2",
                "",
                "1.3",
                "4"
            )
        )
        dataList.add(
            ChemicalElement(
                orangeBackground,
                "Protactini",
                "Pa",
                91,
                10,
                "231.04",
                "[Rn] .5f2.6d1.7s2",
                "",
                "1.5",
                "4,5"
            )
        )
        dataList.add(
            ChemicalElement(
                orangeBackground,
                "Urani",
                "U",
                92,
                10,
                "238.03",
                "[Rn] .5f3.6d1.7s2",
                "",
                "1.38",
                "[3],4,[5],6"
            )
        )
        dataList.add(
            ChemicalElement(
                orangeBackground,
                "Neptuni",
                "Np",
                93,
                10,
                "[237]",
                "[Rn] .5f4.6d1.7s2",
                "",
                "1.36",
                "[3],4,5,6"
            )
        )
        dataList.add(
            ChemicalElement(
                orangeBackground,
                "Plutoni",
                "Pu",
                94,
                10,
                "[244]",
                "[Rn] .5f6.6d0.7s2",
                "",
                "1.28",
                "[3],4,5,6"
            )
        )
        dataList.add(
            ChemicalElement(
                orangeBackground,
                "Amerixi",
                "Am",
                95,
                10,
                "[243]",
                "[Rn] .5f7.6d0.7s2",
                "",
                "1.13",
                "[3],4,5,6"
            )
        )
        dataList.add(
            ChemicalElement(
                orangeBackground,
                "Curi",
                "Cm",
                96,
                10,
                "[247]",
                "[Rn] .5f7.6d1.7s2",
                "",
                "1.28",
                "3"
            )
        )
        dataList.add(
            ChemicalElement(
                orangeBackground,
                "Beckeli",
                "Bk",
                97,
                10,
                "[247]",
                "[Rn] .5f9.6d0.7s2",
                "",
                "1.3",
                "3,4"
            )
        )
        dataList.add(
            ChemicalElement(
                orangeBackground,
                "Califoni",
                "Cf",
                98,
                10,
                "[251]",
                "[Rn] .5f10.6d0.7s2",
                "",
                "1.3",
                "3"
            )
        )
        dataList.add(
            ChemicalElement(
                orangeBackground,
                "Ensteni",
                "Es",
                99,
                10,
                "[252]",
                "[Rn] .5f11.6d0.7s2",
                "",
                "1.3",
                "3"
            )
        )
        dataList.add(
            ChemicalElement(
                orangeBackground,
                "Fecmi",
                "Fm",
                100,
                10,
                "[257",
                "[Rn] .5f12.6d0.7s2",
                "",
                "1.3",
                "3"
            )
        )
        dataList.add(
            ChemicalElement(
                orangeBackground,
                "Menđêlevi",
                "Md",
                101,
                10,
                "[258",
                "[Rn] .5f13.6d0.7s2",
                "",
                "1.3",
                "2,3"
            )
        )
        dataList.add(
            ChemicalElement(
                orangeBackground,
                "Nobeli",
                "No",
                102,
                10,
                "[259]",
                "[Rn] .5f14.6d0.7s2",
                "",
                "1.3",
                "2,3"
            )
        )
        dataList.add(
            ChemicalElement(
                orangeBackground,
                "Lorenxi",
                "Lr",
                103,
                10,
                "[260]",
                "[Rn] .5f14.6d1.7s2",
                "",
                "",
                "3"
            )
        )

        dataList.add(EmptySpace())

        // Line 11
        dataList.add(EmptySpace())

        return dataList
    }


}