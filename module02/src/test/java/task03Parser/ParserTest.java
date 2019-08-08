package task03Parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import task03XMLparser.model.Speech;
import task03XMLparser.parser.DomParser;
import task03XMLparser.parser.SaxParser;
import task03XMLparser.utils.PlayAnaliser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ParserTest {

    //{the=7246, and=4842, of=4819, I=4252, to=4079, a=3611, in=2556, my=2534, it=2338, you=2214, not=1972, this=1923, his=1850, is=1806, that=1693, me=1671, And=1667, s=1656, d=1531, =1477, have=1451, be=1449, That=1335, for=1298, with=1297, as=1142, but=1047, To=1030, your=1020, all=1004, he=979, so=942, The=887, will=843, ll=805, O=785, do=784, or=769, what=766, him=714, on=692, like=690, no=682, by=648, t=639, But=630, are=602, was=600, more=595, With=570, at=564, would=558, we=541, A=536, most=518, am=518, As=498, king=490, from=489, thee=488, very=487, father=478, thou=475, her=464, such=461, now=460, let=454, if=451, make=445, For=444, should=442, one=440, man=439, heaven=435, know=426, an=424, thy=421, us=421, What=420, good=406, shall=403, their=393, come=384, them=379, play=374, own=373, they=372, soul=371, eyes=358, heart=351, say=351, may=342, This=339, too=332, villain=331, well=322, must=313, some=313, My=306, night=302, our=301, there=298, Why=298, nothing=293, o=290, So=288, time=286, mine=285, who=285, God=283, mother=280, When=277, cannot=276, than=276, nature=276, how=273, sleep=272, might=262, see=262, Is=258, speak=257, death=252, Or=251, speech=242, much=241, reason=241, sir=240, why=238, go=234, life=234, out=234, murder=233, How=233, yet=231, here=229, Now=228, No=228, thing=221, thus=218, hath=218, could=218, devil=217, though=215, she=215, earth=215, whose=212, tongue=211, passion=209, upon=208, remember=204, dear=203, love=201, uncle=200, which=199, It=198, er=198, off=197, tis=195, take=193, Let=193, damn=193, Hecuba=191, hell=191, indeed=190, then=189, Be=188, set=187, give=186, Hamlet=184, fortune=184, does=181, hold=181, If=181, sense=179, two=178, did=178, had=176, down=175, men=175, these=174, conscience=174, players=173, made=172, face=172, matter=171, blood=170, He=170, himself=169, live=168, madness=168, cause=167, fair=167, poor=167, Horatio=166, follow=166, bear=163, without=162, part=162, slave=162, itself=161, In=161, great=159, Ay=158, against=158, were=155, grace=153, can=153, heard=152, those=152, spirit=149, guilty=149, tell=149, word=149, thought=149, world=149, again=147, think=144, end=143, nor=143, Wherein=142, Would=141, You=141, lord=140, Could=139, revenge=139, where=139, tears=139, bloody=138, Where=137, Pyrrhus=136, gives=135, lady=134, One=134, after=133, They=132, when=132, makes=132, before=131, Give=131, thoughts=130, Must=129, shame=129, seen=128, within=127, Have=127, doth=124, wrong=124, brother=123, conceit=122, general=122, catch=122, hear=122, i=121, action=121, form=121, welcome=120, act=119, look=118, proclaim=118, virtue=118, thousand=116, something=116, excellent=116, up=116, away=115, ear=115, Look=115, never=115, censure=114, feed=113, others=112, old=112, ere=111, sure=111, There=110, Who=110, into=110, beast=109, use=108, By=108, pray=107, judgment=107, Which=107, once=106, dreams=105, name=105, May=105, Nor=104, fine=103, twas=103, Make=103, Had=103, damned=103, month=102, Ha=102, break=102, son=102, dream=101, seem=101, god=100, voice=100, Nay=99, call=99, Like=98, coward=98, dull=98, dead=98, Since=97, shape=97, Of=97, words=97, memory=96, pate=96, find=96, leave=96, seal=96, struck=95, mad=95, fire=95, forms=95, alone=95, cunning=95, spirits=94, else=94, scene=94, Fie=93, Go=93, ever=92, arms=92, lie=92, put=92, comes=92, Good=92, whole=92, show=92, charge=92, en=92, little=91, honour=90, other=90, quick=89, phrase=89, while=89, woman=88, die=88, beard=88, His=88, More=88, Hath=87, sickly=87, lack=86, queen=86, Then=86, visage=86, free=86, horrid=86, course=86, day=85, modesty=85, Than=84, Upon=84, deep=84, brain=83, Come=82, custom=82, body=82, takes=82, weep=82, fellow=81, about=81, brave=81, distraction=81, goes=81, discourse=80, friends=79, black=79, creatures=79, dread=78, husband=78, Whose=78, hands=78, Tis=78, saw=78, Thou=77, ears=77, trick=77, enough=77, gave=77, feeling=77, ghost=77, mind=76, bitter=76, Denmark=76, fly=76, wholesome=76, choice=75, Here=75, work=75, kind=75, proceed=74, hast=74, event=74, Do=74, panders=74, age=74, worth=74, flesh=74, looks=74, married=74, head=74, top=73, Not=73, organ=73, Swounds=73, honest=73, mortal=73, youth=72, circumstance=72, assume=72, means=72, neither=72, ass=72, bed=72, woo=72, perhaps=72, sins=72, Yea=71, We=71, rogue=71, From=71, ye=71, turn=70, Even=70, lungs=70, purpose=70, laugh=69, gross=69, Thus=69, melt=69, true=69, sweet=69, Hyperion=69, both=69, ay=69, peasant=69, potent=69, note=69, Priam=68, stand=68, weary=68, fall=68, defeat=68, noble=68, st=68, rugged=68, judgments=67, stop=67, throat=67, Yet=67, breaks=67, complexion=67, force=67, power=67, nose=67, infinite=66, has=66, flatter=66, seeming=66, slaughter=66, perchance=66, even=65, command=65, Am=65, foul=65, calls=64, long=64, Makes=64, native=63, pith=63, grave=63, bones=63, throw=62, tame=62, dust=62, vengeance=61, same=61, faculties=61, About=61, motive=61, peak=61, John=61, question=61, scullion=61, function=61, pigeon=61, fatted=61, working=61, unpack=61, marry=61, cue=61, Tweaks=61, wann=61, Plucks=61, blows=61, yea=61, muddy=61, fiction=61, Remorseless=61, tent=61, cursing=61, monstrous=61, relative=61, stage=61, suiting=61, whore=61, drown=61, ignorant=61, melancholy=61, grounds=61, Out=61, bawdy=61, blench=61, across=61, appal=61, kites=61, Been=61, aspect=61, oppression=61, property=61, Prompted=61, weakness=61, amaze=61, Confound=61, broken=61, presently=61, treacherous=61, incestuous=61, liver=61, cleave=61, miraculous=61, pleasing=61, gall=61, mettled=61, observe=61, foh=61, drab=61, rascal=61, Before=61, kindless=61, fight=61, sitting=61, unpregnant=61, lecherous=61, region=61, wi=61, Tears=61, Abuses=61, malefactions=61, Play=61, offal=61, player=61, loved=60, nunnery=60, things=60, Sir=60, wherefore=60, suffer=60, Laertes=60, sea=60, horse=60, begins=59, lend=59, particular=59, answer=59, next=59, pleased=59, keep=59, England=58, pardon=58, first=58, any=58, rest=58, Alexander=58, sword=57, straight=56, speaks=56, better=56, wicked=56, Dost=56, nay=56, cast=56, piece=56, disposition=56, said=56, meet=55, forth=55, kill=55, rank=55, try=54, horridly=54, knave=54, lines=54, fie=54, hide=54, scandal=54, help=54, pale=53, many=53, near=53, eye=53, beg=53, ta=53, hand=52, times=52, mistress=52, three=52, heavy=52, stamp=52, mercy=52, still=52, livery=52, wear=52, whether=51, pipe=51, every=51, lose=51, quantity=51, At=51, neck=51, Soft=51, line=51, days=50, been=50, twenty=50, mass=50, music=50, blush=50, corruption=50, Swear=50, physic=50, election=50, revenged=50, ha=50, avoid=50, prolongs=50, natural=50, lay=49, done=49, bless=49, drink=49, thinking=49, An=48, ache=48, unworthy=48, sight=48, proud=48, ambition=47, awhile=47, danger=47, myself=47, bad=47, peace=47, between=47, last=46, whereto=46, quality=46, respect=46, air=46, longer=46, uses=46, e=45, knows=45, punish=45, thanks=45, table=45, angel=45, dare=45, sound=45, only=45, sworn=44, picture=44, smile=44, mark=44, Remember=44, Ophelia=44, house=43, Jove=43, Being=43, suffers=43, straw=43, together=43, eat=43, kissing=43, wisdom=42, Ere=42, Their=42, Heaven=42, merit=42, imitated=42, manners=42, overdone=42, humanity=42, oft=42, abominably=42, smiling=42, full=41, right=41, shoes=41, rather=41, brothers=41, hope=41, assurance=41, rewards=41, shell=41, sweat=41, yourself=41, grow=41, galled=41, follows=41, till=40, grown=40, Say=40, past=40, cruel=40, Up=40, hurt=40, parts=40, burst=40, months=40, ne=40, country=40, canst=40, noise=39, comest=39, desire=39, further=39, habit=39, troubles=39, thrift=39, please=39, prithee=39, image=39, Mother=39, sent=39, Hercules=38, evil=38, grows=38, tale=38, born=38, fed=38, matters=38, beauty=38, begin=38, passionate=38, Such=38, Sure=37, reserved=37, humble=37, fantasy=37, Eyes=37, sans=37, Excitements=37, secrecy=37, invisible=37, smelling=37, Else=37, New=37, army=37, burn=37, chief=37, mountain=37, Blasting=37, difference=37, actions=37, Exposing=37, Rebellious=37, hey=37, batten=37, fust=37, Sith=37, garden=37, exhort=37, combination=37, Will=37, craven=37, corse=37, See=37, beds=37, mildew=37, capability=37, Your=37, quarter=37, seated=37, occasions=37, prince=37, ecstasy=37, herald=37, apoplex=37, serve=37, Whereon=37, stain=37, stake=37, inform=37, brow=37, numbers=37, slain=37, step=37, cozen=37, Examples=37, Sense=37, stir=37, fame=37, Ears=37, matron=37, egg=37, advancement=37, unused=37, precisely=37, Looking=37, Witness=37, graves=37, tender=37, err=37, tomb=37, spur=37, Led=37, imminent=37, continent=37, Rightly=37, curls=37, strength=37, flaming=37, blind=37, Mars=37, wax=37, Mercury=37, delicate=37, frost=37, mope=37, waits=37, moon=37, moor=37, thrall=37, hoodman=37, threaten=37, market=37, unsure=37, ardour=37, Bestial=37, front=37, mutine=37, greatly=37, fool=37, plot=37, mouths=37, divine=37, compulsive=37, large=37, puff=37, quarrel=37, lighted=37, actively=37, hill=37, scruple=37, counterfeit=37, oblivion=37, argument=37, presentment=37, station=37, motion=37, currents=36, wish=36, Devoutly=36, quietus=36, regard=36, arrows=36, suits=36, law=36, nobler=36, moment=36, opposing=36, pause=36, under=36, She=36, puzzles=36, slings=36, awry=36, hue=36, Nymph=36, curb=36, sicklied=36, bare=36, undiscover=36, Whether=36, resolution=36, returns=36, shocks=36, need=36, calamity=36, oppressor=36, orisons=36, grunt=36, spurns=36, rub=36, outrageous=36, fardels=36, despised=36, contumely=36, patient=36, consummation=36, pangs=36, fix=36, delay=36, bodkin=36, needs=36, scorns=36, whips=36, office=36, enterprises=36, cowards=36, shuffled=36, ills=36, insolence=36, coil=36, heir=36, traveller=36, bourn=36, wilt=36, Elsinore=35, health=35, since=35, royal=35, took=35, faith=35, purging=35, behind=35, souls=35, pure=35, resemble=34, tyrannous=34, impasted=34, scenes=34, gore=34, million=34, sallets=34, couched=34, digested=34, sable=34, thereabout=34, Dane=34, light=34, gules=34, daughters=34, carbuncles=34, mean=34, mothers=34, soft=34, hellish=34, smear=34, business=34, parching=34, loam=34, basket=34, sons=34, handsome=34, sized=34, Old=34, above=34, received=34, Baked=34, seeks=34, affectation=34, fingers=34, indict=34, caviare=34, chiefly=34, called=34, fathers=34, cried=34, ignorance=34, Aeneas=34, Black=34, foot=34, grandsire=34, Couch=34, Hyrcanian=34, coagulate=34, savoury=34, acted=34, author=34, heraldry=34, total=34, dismal=34, especially=34, streets=34, wrath=34, method=34, ominous=34, Dido=34, roasted=34, half=33, commingled=33, imaginations=33, afoot=33, crook=33, Vulcan=33, doubt=33, pair=33, equal=33, candied=33, unkennel=33, hinges=33, absurd=33, Hast=33, occulted=33, blest=33, revenue=33, breath=33, fawning=33, distinguish=33, suffering=33, comment=33, finger=33, seest=33, pomp=33, Observe=33, French=33, told=33, pregnant=33, guilt=33, buffets=33, clothe=33, mouth=33, stithy=33, fit=33, rivet=33, join=33, knee=33, Something=33, buried=33, Never=33, heedful=33, lick=33, gentleman=33, blow=33, core=33, herself=33, solid=32, left=32, hang=32, salt=32, resolve=32, knavery=32, room=32, loving=32, herods=32, almost=32, Thaw=32, dexterity=32, These=32, Seem=32, merely=32, gainst=32, dew=32, fines=32, sheets=32, stale=32, Niobe=32, adieu=32, roughly=32, speed=32, wants=32, Everlasting=32, seems=32, winds=32, unweeded=32, post=32, beteem=32, Hold=32, vouchers=32, Visit=32, unprofitable=32, appetite=32, mourn=32, fat=32, double=32, Possess=32, Herod=32, increase=32, canon=32, ah=32, Gonzago=32, recoveries=32, unrighteous=32, flushing=32, box=32, seed=32, Frailty=32, proof=32, wife=32, inheritor=32, flat=32, self=32, satyr=32, Once=31, mirror=31, star=31, imagination=31, vice=31, Shall=31, report=31, express=31, none=31, send=31, audience=30, yours=30, west=30, philosophy=30, pass=30, easy=30, seals=30, wrote=30, consent=30, another=30, mole=30, fond=30, teach=30, place=30, chances=30, wind=29, Was=29, aught=29, dost=29, Well=29, relish=29, believe=29, baser=29, blessing=29, either=29, suit=29, heels=29, pleasure=29, ground=29, commission=28, tear=28, glad=28, tables=28, special=28, shake=28, already=28, madam=28, bid=28, Farewell=28, odd=28, change=28, dirt=28, marrow=27, r=27, vicious=27, strange=27, perform=27, observance=27, birth=27, traduced=27, manner=27, wherein=27, eale=27, east=27, virtues=27, pales=27, reaches=27, headed=27, Oft=27, height=27, undergo=27, silence=27, drunkards=27, ergrowth=27, Doth=27, Soil=27, therefore=27, choose=27, forts=27, defect=27, swinish=27, leavens=27, achievements=27, enemy=27, came=27, attribute=27, substance=27, way=27, borne=27, breach=27, addition=27, origin=27, ho=27, nations=27, dram=27, Carrying=27, plausive=27, draw=27, clepe=27, breaking=27, revel=27, tax=27, fault=27, lips=26, thank=26, making=26, service=26, Get=26, secret=26, sick=26, friend=26, salary=25, drunk=25, tardy=25, m=25, wise=25, pat=25, worser=25, save=25, honesty=25, gaming=25, less=25, desirous=25, crimes=25, season=25, blown=25, grossly=25, stays=25, fellowship=25, easiness=25, trip=25, sole=25, hole=25, thick=25, purer=25, salvation=25, kills=25, repent=25, bestow=25, aptly=25, sweep=25, minister=25, frock=25, Refrain=25, potency=25, ape=25, kick=25, remains=25, worse=25, Leave=25, far=25, broad=25, wanton=25, rage=25, habits=25, wondrous=25, died=25, bread=25, likewise=25, stands=25, audit=25, monster=25, scourge=25, hire=25, flush=25, scann=25, asleep=25, swearing=25, praying=25, passage=25, hent=25, Assume=25, abstinence=25, majesty=24, Woo=24, delights=24, fools=24, farewell=24, Indeed=24, defend=24, effect=24, rant=24, woe=24, lt=24, get=24, gentlemen=24, purposed=24, trappings=24, quite=23, skull=23, Danish=23, goodly=23, guts=23, mock=23, ring=23, capable=23, foolish=23, prison=22, wipe=22, Adieu=22, shriving=22, instant=22, All=22, saws=22, pernicious=22, land=22, Unmix=22, giving=22, yes=22, sinews=22, host=22, hither=22, holds=22, allow=22, books=22, craft=22, least=22, couple=22, volume=22, copied=22, estate=22, through=22, Into=22, book=22, news=22, Within=22, pressures=22, stiffly=22, visit=22, bring=22, globe=22, epitaph=22, seat=22, stops=22, trivial=22, records=22, observation=22, commandment=22, distracted=22, twere=21, tutor=21, grieve=21, playing=21, having=21, unskilful=21, journeymen=21, pressure=21, theatre=21, erstep=21, scorn=21, grief=21, judicious=21, jest=21, praise=21, pagan=21, strutted=21, gait=21, erweigh=21, bellowed=21, discretion=21, Christians=21, highly=21, Christian=21, profanely=21, allowance=21, shapes=21, feature=21, lead=21, accent=21, paragon=20, blasts=20, airs=20, humbly=20, hearsed=20, anticipation=20, roof=20, majestical=20, promontory=20, firmament=20, mirth=20, arrow=20, bulwark=20, Angels=20, generous=20, goblin=20, quietly=20, disclaiming=20, canopy=20, late=20, cerements=20, golden=20, ponderous=20, Making=20, heavily=20, faculty=20, hideous=20, length=20, faction=20, quintessence=20, warlike=20, backward=20, sore=20, appears=20, oped=20, admirable=20, pestilent=20, charitable=20, lost=20, steel=20, sepulchre=20, congregation=20, forgone=20, umbrage=20, exception=20, moving=20, glimpses=20, prevent=20, sterile=20, vapours=20, intents=20, Revisit=20, jaws=20, erhanging=20, Bring=20, Free=20, shot=20, canonized=20, Speak=20, questionable=20, denies=20, awake=20, ministers=20, unsatisfied=20, em=20, marble=20, frame=20, sit=20, feather=20, animals=20, discovery=20, complete=20, moult=20, exercises=20, Roughly=20, apprehension=20, maker=20, ve=20, beyond=20, presence=20, King=20, fretted=20, ready=20, inurn=20, masters=19, gold=19, young=19, chopine=19, Sblood=19, colour=19, lot=19, altitude=19, apiece=19, pious=19, falconers=19, Pray=19, stay=19, valenced=19, abridgement=19, ill=19, ladyship=19, row=19, uncurrent=19, taste=19, cracked=19, nearer=19, wot=19, chanson=19, Prithee=19, Alas=19, Masters=19, marriage=19, Welcome=19, Lucianus=18, cry=18, watch=18, trespass=18, Ecstasy=18, hew=18, ways=18, Whilst=18, Confess=18, Rough=18, hot=18, pursy=18, test=18, mattering=18, Virtue=18, fear=18, skin=18, leisure=18, unseen=18, film=18, ranker=18, Infects=18, themselves=18, nephew=18, mining=18, ulcerous=18, weeds=18, compost=18, Lay=18, Forgive=18, towering=18, Repent=18, jig=18, truly=18, gain=18, temperately=18, arrant=18, spread=18, healthful=18, unction=18, pulse=18, re=18, fatness=18, gambol=18, utter=18, foils=18, cat=18, played=18, Whom=17, mines=17, aside=17, hard=17, cheek=17, famous=17, loves=17, ravel=17, tempt=17, bloat=17, despite=17, wall=17, sport=17, Should=17, clay=17, withal=17, converted=17, prating=17, engineer=17, Twere=17, conclusions=17, barrel=17, thither=17, lug=17, beer=17, court=17, petard=17, schoolfellows=17, creep=17, rites=17, letters=17, patch=17, concernings=17, sober=17, expel=17, fell=17, returneth=17, worm=17, toward=17, birds=17, forgot=17, fang=17, winter=17, Pinch=17, desperate=17, counsellor=17, Might=17, its=17, trust=17, wouldst=17, Caesar=17, likelihood=17, below=17, mandate=17, adders=17, Follow=17, mouse=17, awe=17, yard=17, Unpeg=17, jot=17, maimed=17, bat=17, betoken=17, packing=17, false=17, kept=17, crafts=17, neighbour=17, marshal=17, directly=17, kisses=17, essentially=17, Hoist=17, delve=17, willing=17, reechy=17, paddock=17, Imperious=17, Fordo=17, courtiers=17, flaw=17, paddling=17, gib=17, spoke=16, encumber=16, bubbles=16, list=16, breadth=16, conveyances=16, succession=16, doubtful=16, walk=16, trippingly=16, groundlings=16, exclaim=16, battery=16, shovel=16, dumbshows=16, pronounced=16, Termagant=16, offends=16, shows=16, request=16, solicited=16, hereafter=16, ambiguous=16, whipped=16, buyer=16, deceived=16, outward=16, torrent=16, statutes=16, dry=16, crier=16, forget=16, best=16, erdoing=16, rude=16, except=16, dirty=16, recognizances=16, murderer=16, Does=16, knock=16, pated=16, periwig=16, temperance=16, quiddities=16, pronouncing=16, tricks=16, hobby=16, Ah=16, quillets=16, whirlwind=16, hardly=16, vouch=16, headshake=16, trial=16, dreamt=16, gently=16, acquire=16, town=16, stranger=16, quickly=16, tenures=16, rags=16, lawyer=16, comply=16, Did=16, inexplicable=16, antic=16, deed=16, aunt=16, Hum=16, tatters=16, rash=16, sconce=16, smoothness=16, soe=16, split=16, beget=16, robustious=16, lands=16, indentures=16, purchases=16, cases=16, lief=16, ones=16, seeing=16, recovery=16, tempest=16, bugs=15, methinks=15, witching=15, flashes=15, gown=15, wounded=15, each=15, found=15, bated=15, Finger=15, fallen=15, enter=15, chap=15, gambols=15, grinding=15, inch=15, fears=15, back=15, grand=15, hypocrites=15, dog=15, unseal=15, firm=15, churchyards=15, unnatural=15, wont=15, reasons=15, commend=15, Yorick=15, gorge=15, chamber=15, bosom=15, sorts=15, packet=15, fast=15, goblins=15, shent=15, breathes=15, gibes=15, favour=15, On=15, waves=15, jaw=15, rims=15, Larded=15, supervise=15, forgetting=15, bold=15, fancy=15, axe=15, songs=15, hung=15, wit=15, yawn=15, soever=15, withdrew=15, scarf=15, Contagion=15, flourish=15, easily=15, knew=15, several=15, kissed=15, paint=15, cabin=15, Groped=15, merriment=15, Nero=15, story=15, quake=15, sing=15, Importing=15, roar=15, exact=15, dark=15, daggers=15, grinning=15, favours=15, abhorred=15, faces=14, pocket=14, writ=14, want=14, imponed=14, opposites=14, anon=14, gracious=14, meant=14, leaves=14, prologue=14, depart=14, trace=14, swear=14, mighty=14, Yes=14, daughter=14, six=14, picked=14, praised=14, beggar=14, betimes=14, sets=13, Marry=13, rose=13, Calls=13, religion=13, blister=13, tristful=13, vows=13, doom=13, innocent=13, contraction=13, plucks=13, known=13, glow=13, dangerous=13, rhapsody=13, compound=13, hypocrite=13, solidity=13, ducats=13, forehead=13, morrow=13, blurs=13, foil=13, freely=13, dicers=13, oaths=13, courtier=13, spacious=12, outface=12, instrument=12, breeder=12, toe=12, Very=12, ambitious=12, lest=12, knavish=12, burning=12, moods=12, pluck=12, fellows=12, wart=12, prate=12, revengeful=12, havior=12, possession=12, denote=12, zone=12, beck=12, acres=12, offences=12, inky=12, Singeing=12, compass=12, Seems=12, mew=12, thyself=12, Murder=12, fret=12, forced=12, common=12, Ossa=12, sinners=12, windy=12, Take=12, crawling=12, sorry=12, toil=12, eisel=12, pledge=12, mystery=12, accuse=12, triumph=12, loggats=12, crocodile=12, lowest=12, erwhelm=12, indifferent=12, solemn=12, dies=12, Millions=12, drive=12, sponge=12, deal=12, river=12, leaping=12, stairs=12, Though=12, Wittenberg=12, mountains=12, justly=12, dejected=12, customary=12, Together=12, pajock=12, whine=12, lobby=12, easier=12, suspiration=12, Call=12, cloak=12, prophesy=12, wiseness=12, fruitful=12, passeth=12, knaves=12, hits=12, whatsoever=11, conceal=11, eleven=11, garland=11, faithful=11, requite=11, tween=11, twixt=11, hitherto=11, violence=11, lets=11, fashion=11, hap=11, palm=11, offence=11, point=11, tenable=11, tributary=11, recorders=11, maggots=11, conjuration=11, contents=11, Between=11, sleeps=11, stiff=11, knowing=11, comma=11, spite=11, platform=11, wheaten=11, fare=11, understanding=11, perturbed=11, forty=11, odds=11, debatement=11, friending=11, poison=11, view=11, lived=11, carriages=11, bearers=11, Without=11, win=11, sudden=11, earnest=11, gape=11, es=11, carry=11, cursed=11, amities=11, twelve=11, sun=11, joint=11, person=11, Rest=11, Things=10, yourselves=10, rareness=10, plentiful=10, hams=10, Devised=10, lisp=10, Over=10, paintings=10, considered=10, write=10, felicity=10, reform=10, gum=10, semblable=10, busy=10, says=10, wring=10, villanies=10, cup=10, yaw=10, standing=10, friendship=10, given=10, pain=10, unknown=10, barren=10, infusion=10, tree=10, strict=10, amble=10, netted=10, perdition=10, wretched=10, Lady=10, verity=10, amber=10, dearth=10, beaten=10, extolment=10, wrinkled=10, sergeant=10, dizzy=10, mutes=10, nick=10, sends=10, altogether=10, yeoman=10, choler=10, round=10, article=10, liest=10, crab=10, arithmetic=10, brains=10, nasty=10, new=10, brass=10, baseness=10, employment=10, livest=10, diction=10, inventorially=10, didst=10, wringing=10, definement=10, grey=10, pitiful=10, counsel=10, harsh=10, powerfully=10, labour=10, penetrable=10, stuff=10, arrest=10, satirical=10, Absent=10, sat=10, statists=10, gets=10, Guildenstern=10, Consent=10, wantonness=10, actor=10, potently=10, marriages=10, divide=10, Slanders=10, Wretched=10, plum=10, aright=10, villanous=10, chance=10, spectators=10, sty=10, intruding=10, rt=10, seek=10, necessary=10, clowns=10, learning=10, weak=10, tremble=10, begun=10, beards=10, sail=10, cost=10, ten=9, sere=9, halt=9, razed=9, hart=9, lover=9, stricken=9, mutines=9, knight=9, quit=9, unwrung=9, feathers=9, rashness=9, Baptista=9, ends=9, canker=9, short=9, bravery=9, pall=9, trap=9, Rashly=9, safely=9, touches=9, purse=9, sometimes=9, Turk=9, blank=9, fortunes=9, adventurous=9, signet=9, being=9, bilboes=9, impression=9, Folded=9, serves=9, likes=9, Vienna=9, Provincial=9, Subscribed=9, fish=9, proper=9, tribute=9, Our=9, Thrown=9, cozenage=9, hopes=9, runs=9, wince=9, perfect=9, jade=9, heartily=9, forest=9, Worse=9, Mouse=9, duke=9, plots=9, withers=9, arm=9, indiscretion=9, target=9, sequent=9, changeling=9, clown=9, ungalled=9, Popp=9, methought=9, deer=9, whored=9, placed=9, model=9, angle=9, sigh=9, portraiture=9, ordinant=9, boy=9, plays=9, fighting=9, gratis=9, humourous=9, tickled=9, Tropically=9, divinity=9, roses=9, verse=9, interim=9, guards=8, croaking=8, lights=8, got=8, scholars=8, provided=8, bung=8, raven=8, lap=8, snow=8, ceremony=8, pursue=8, whensoever=8, Fortinbras=8, entertainment=8, Jephthah=8, appurtenance=8, escape=8, dying=8, bellow=8, heavens=8, hours=8, Touching=8, keeps=8, encounter=8, Patrick=8, build=8, fiery=8, eats=8, escoted=8, forgotten=8, vision=8, Stick=8, swallowed=8, dowry=8, Gentlemen=8, acting=8, beggars=8, yesty=8, able=8, clouts=8, writers=8, dug=8, opinions=8, dotes=8, wonderful=8, bevy=8, heavenly=8, appear=8, ice=8, beseech=8, collection=8, sucked=8, swaddling=8, chaste=8, gleaned=8, politic=8, outlive=8, emperor=8, inmost=8, ourselves=8, replication=8, equivocation=8, wings=8, sirrah=8, variable=8, year=8, fairly=8, authorities=8, ermaster=8, soldiers=8, undo=8, ago=8, years=8, winnowed=8, shalt=8, mouthed=8, Impart=8, kibe=8, convocation=8, taken=8, art=8, absolute=8, countenance=8, Saint=8, fay=8, soaks=8, Lord=8, card=8, Friend=8, dishes=8, certain=8, important=8, eaten=8, insert=8, diet=8, extent=8, sables=8, plague=8, afterwards=8, dressy=8, admiration=8, garb=8, worms=8, crows=8, corner=8, portal=8, gaffs=8, tongues=8, afeard=8, squeezing=8, stopping=8, sum=8, heel=8, maintains=8, figure=8, passing=8, children=8, monsters=8, churches=8, lean=8, occurrents=8, calumny=8, officers=8, tune=8, carries=8, german=7, fifty=7, lion=7, liberal=7, Nemean=7, swords=7, incensed=7, hundred=7, kings=7, obligation=7, twentieth=7, Unhand=7, obey=7, Against=7, thine=7, preserved=7, hangers=7, affair=7, truster=7, truant=7, conceited=7, convert=7, soon=7, conjoin=7, Still=7, insinuation=7, Lest=7, cries=7, petty=7, augury=7, consonancy=7, stern=7, Barbary=7, circumvent=7, cannon=7, rule=7, glares=7, politician=7, providence=7, tithe=7, abstract=7, horses=7, empire=7, fate=7, precedent=7, assigns=7, shelf=7, hardy=7, chronicles=7, rights=7, points=7, bestowed=7, piteous=7, jowls=7, bone=7, direct=7, sides=7, whit=7, used=7, effects=7, nerve=7, mows=7, artery=7, readiness=7, bet=7, cutpurse=7, defy=7, Nothing=7, brief=7, conjure=7, Cain=7, stones=7, diadem=7, stole=7, cold=7, precious=7, Excellent=7, proposer=7, preaching=7, sparrow=7, hall=6, kettle=6, meats=6, southerly=6, baked=6, reigns=6, conversation=6, remove=6, twill=6, lads=6, immortal=6, carrion=6, Keeps=6, laid=6, messenger=6, wake=6, pin=6, whipping=6, hawk=6, bodykins=6, university=6, Treachery=6, followed=6, state=6, Hear=6, splenitive=6, Rhenish=6, Seek=6, wealth=6, mazzard=6, hit=6, deserve=6, approve=6, dearest=6, sometime=6, beasts=6, rouse=6, grass=6, wedding=6, drains=6, desert=6, promise=6, Rome=6, bray=6, worthy=6, pound=6, Perchance=6, bounty=6, sings=6, bent=6, Were=6, Roscius=6, sooner=6, bonnet=6, mess=6, transform=6, paradox=6, met=6, chough=6, crammed=6, funeral=6, knocked=6, progress=6, reputation=6, visitation=6, admit=6, Till=6, revolution=6, bawd=6, likeness=6, spade=6, daintier=6, trade=6, ashamed=6, hasten=6, strumpet=6, handsaw=6, Thy=6, coped=6, spring=6, sexton=6, city=6, swaggering=6, musty=6, Beggar=6, frontier=6, crib=6, halfpenny=6, puppets=6, shift=6, deeds=6, Two=6, fertile=6, debate=6, trumpet=6, worst=6, wag=6, Put=6, rise=6, northerly=6, capons=6, Worm=6, Hic=6, eyelids=6, fee=6, perceive=6, wards=6, profit=6, Until=6, Rosencrantz=6, Marcellus=6, coldly=6, inward=6, furnish=6, Thrift=6, rawer=6, dallying=6, et=6, foe=6, trouble=6, proverb=6, breathing=6, Didst=6, ubique=6, realm=6, withdraw=6, breeding=6, brought=6, imposthume=6, wassail=6, pioner=6, side=6, Use=6, dismantled=6, draughts=6, recover=6, dungeons=6, Another=6, Damon=6, weaker=6, venom=6, inclining=6, scape=6, north=6, drum=6, translate=6, chapless=6, While=6, reels=6, dignity=6, govern=5, Bears=5, went=5, doctor=5, Italian=5, stars=5, extant=5, ventages=5, purgation=5, emphasis=5, hearers=5, confession=5, lying=5, comedy=5, sort=5, sorrow=5, doomsday=5, cellarage=5, Conjures=5, wonder=5, richer=5, lingers=5, thumb=5, eloquent=5, signify=5, France=5, honeying=5, deserved=5, poisons=5, servants=5, Stew=5, perdy=5, plunge=5, attended=5, wandering=5, modesties=5, enseamed=5, diseased=5, dreadfully=5, belike=5, truepenny=5, practise=5, continual=5, Methinks=4, legs=4, hearer=4, union=4, grizzled=4, pah=4, monarchs=4, soles=4, treasure=4, rood=4, heroes=4, camel=4, shadows=4, Hide=4, frankly=4, Judgment=4, chide=4, stock=4, Are=4, Arm=4, pox=4, demanded=4, tedious=4, smelt=4, glass=4, mallecho=4, weapon=4, supper=4, wager=4, shadow=4, happy=4, groaning=4, rusty=4, calves=4, merry=4, Yours=4, imports=4, history=4, water=4, Saw=4, steals=4, frighted=4, sixteen=4, third=4, murderous=4, pickers=4, compare=4, bodies=4, possible=4, dig=4, Monday=4, bird=4, talk=4, count=4, inoculate=4, baby=4, husbands=4, Hark=4, return=4, cheerfully=4, Save=4, conceive=4, hour=4, stealers=4, Haste=4, Buz=4, maids=4, budge=4, commands=4, frowningly=4, base=4, sheepskins=4, believed=4, Pale=4, space=4, miching=4, conception=4, offended=4, red=4, posy=4, fitness=4, swift=4, embrace=4, pronounce=4, strangely=4, judge=4, Madam=4, weapons=4, meditation=4, rot=4, patches=4, damnable=4, Stay=4, enact=4, attractive=4, bounded=4, Safely=4, shoe=4, mobled=4, study=4, Begin=4, mischief=4, nomination=4, Drink=4, nut=4, cloud=4, whale=4, shrewdly=4, darkest=4, sheep=4, boys=4, yonder=4, fishmonger=4, Israel=4, parchment=4, stowed=4, fox=4, hover=4, skill=4, instructs=4, outstretched=4, potion=4, bound=4, weasel=4, dally=4, astonish=4, lapsed=4, sequel=4, metal=4, Besides=4, powers=4, constant=4, edge=4, Polack=4, prophetic=4, Hillo=4, morning=4, poisoning=4, bawdry=4, Words=4, excellence=4, buz=4, willingly=4, kin=4, Compounded=4, confess=4, barber=4, reveal=4, dozen=4, hadst=4, bites=4, shreds=4, wrap=3, understand=3, capital=3, offend=3, receive=3, brute=3, theme=3, travel=3, door=3, Goes=3, diligence=3, Bid=3, just=3, interpret=3, lock=3, concernancy=3, main=3, residence=3, coming=3, middle=3, doors=3, haste=3, estimation=3, Ho=3, read=3, looked=3, Poland=3, calf=3, Has=3, chameleon=3, cherub=3, waist=3, shut=3, bout=3, foolery=3, confines=3, breed=3, dish=3, student=3, sultry=3, dwelling=3, sees=3, villany=3, sirs=3, envenom=3, idle=3}

    String path;

    @BeforeEach
    public void init() {
        path = "/home/spluft/Documents/hamlet.xml";
    }

    @Test
    public void SaxParserTest() throws ParserConfigurationException, SAXException, IOException {
        SaxParser saxParser = new SaxParser(path);
        List<Speech> speeches = saxParser.startParse();
        Map<String, Long> currentMap = PlayAnaliser.getCountUniqueWordsBySpeaker(speeches, "HAMLET");

        assertThat(currentMap)
                .containsEntry("the", 7246L)
                .containsEntry("and", 4842L)
                .containsEntry("of", 4819L)
                .containsEntry("recovery", 16L)
                .containsEntry("idle", 3L);
    }

    @Test
    public void DomParserTest() throws ParserConfigurationException, SAXException, IOException {
        List<Speech> speeches = DomParser.getListOfSpeech("/home/spluft/Documents/hamlet.xml");
    }
}
