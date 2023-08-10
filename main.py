
def felszam(num):
    num = num
    print(f"{num}.feladat")


felszam(5)

def hetnapja(ev, ho , nap):

    napok = ["v", "h", "k", "sze","cs", "p", "szo"]
    honapok = [0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4]
    if ho < 3:
        ev = ev -1
    hetnapja = napok[(ev + ev // 4 - ev // 100 + ev // 400 + honapok[ho - 1] + nap) % 7]
    return hetnapja


epizod = {}
epizodok = []
adatok = []


felszam(1)
with open('lista.txt', 'r', encoding='utf-8') as File:
    for sor in File:
        adatok.append(sor.strip())
        if len(adatok) == 5:
            epizod['datum'] = adatok[0]
            epizod['cim'] = adatok[1]
            epizod['evad_ep'] = adatok[2]
            epizod['hossz'] = int(adatok[3])
            if adatok[4] == '1':
                epizod['latta'] = True
            else:
                epizod['latta'] = False

            epizodok.append(epizod)
            epizod = {}
            adatok = []

answare = 0
for epizod in epizodok:
    if epizod['datum'] != "NI":
        answare += 1
felszam(2)
print(f'A listában {answare} db vetítési dátummal rendelkező epizód van.')

felszam(3)
sum = 0
osszido = 0
for epizod in epizodok:
    if epizod['latta']:
        sum += 1
        osszido += epizod['hossz']

ans = sum / len(epizodok) * 100
print(f"A listában lévő epizódok {ans:.2f}%-át látta.")

felszam(4)

nap = osszido // (60 * 24)
ora = osszido % (60 * 24)// 60
perc = osszido % 60

print(f"Sorozatnézéssel {nap} napot {ora} órát és {perc} percet töltött.")
felszam(5)
bedatum = input("Adjon meg egy dátumot! Dátum= ")

for epizod in epizodok:
    if epizod['datum'] <= bedatum and not epizod['latta']:
        print(f"{epizod['evad_ep']}  {epizod['cim']}")


felszam(7)
benap = input("Adja meg a hét egy napját (például cs)! Nap= ")
napisori = set()
for epizod in epizodok:
    if 'NI' not in epizod['datum']:
        epizod_datum = epizod['datum'].split(".")
        epizod_nap = hetnapja(int(epizod_datum[0]), int(epizod_datum[1]),int(epizod_datum[2]))
        if epizod_nap == benap and not napisori.__contains__(epizod['cim']):
            napisori.add(epizod['cim'])
            print(epizod['cim'])

felszam(8)
sorozatok = {}
for epizod in epizodok:
    if sorozatok.get(epizod['cim'], 0):
        sorozatok[epizod['cim']]['darab_resz'] += 1
        sorozatok[epizod['cim']]['ossz_hossz'] += epizod['hossz']
    else:
        sorozatok[epizod['cim']] = {}
        sorozatok[epizod['cim']]['darab_resz'] = 1
        sorozatok[epizod['cim']]['ossz_hossz'] = epizod['hossz']
with open('summa.txt', 'w', encoding='utf-8' ) as summa:
    for adatok in sorozatok:
        print(adatok, sorozatok[adatok]['ossz_hossz'], sorozatok[epizod['cim']]['darab_resz'], file=summa)