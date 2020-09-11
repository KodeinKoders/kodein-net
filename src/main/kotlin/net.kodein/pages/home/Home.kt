package net.kodein.pages.home

import kotlinx.browser.window
import kotlinx.css.*
import net.kodein.charter.kodein
import net.kodein.components.MenuTop
import net.kodein.components.Separator
import net.kodein.pages.home.fragment.Header
import net.kodein.pages.home.fragment.Departments
import net.kodein.pages.home.fragment.Descriptions
import net.kodein.pages.home.fragment.Humans
import net.kodein.utils.getValue
import net.kodein.utils.recursiveOffset
import org.w3c.dom.HTMLElement
import org.w3c.dom.SMOOTH
import org.w3c.dom.ScrollBehavior
import org.w3c.dom.ScrollToOptions
import react.child
import react.dom.p
import react.functionalComponent
import react.useRef
import styled.css
import styled.styledDiv

val Home by functionalComponent {
    val div = useRef<HTMLElement?>(null)

    child(Header) {
        attrs.onScrollClick = {
            val (_, offsetTop) = div.current!!.recursiveOffset()
            val clientHeight = div.current!!.clientHeight
            window.scrollTo(ScrollToOptions(top = (offsetTop + clientHeight).toDouble(), behavior = ScrollBehavior.SMOOTH))
        }
    }

    styledDiv {
        ref = div
        css {
            backgroundColor = Color.kodein.dark
            padding(1.rem, 3.rem, 0.rem, 3.rem)
        }
        styledDiv {
            css {
                backgroundColor = Color.kodein.orange
                height = 0.05.rem
            }
        }
    }

    child(MenuTop)

    child(Departments)

    child(Separator)

    child(Descriptions)

    child(Humans)


    styledDiv {
        css {
            height = 20.em
        }
    }

//    p { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut sodales sem quis urna dapibus lobortis. Suspendisse vitae luctus leo. Curabitur mattis dui lorem. Ut ultricies egestas magna sed sagittis. Vestibulum vulputate iaculis aliquam. Etiam odio tortor, consequat sed mollis semper, vulputate in nisi. Suspendisse massa leo, euismod a tellus nec, faucibus commodo est. Pellentesque massa libero, faucibus at faucibus sit amet, dapibus sed massa. Integer urna lectus, varius et lacus gravida, euismod pulvinar justo. Morbi a elit nibh. Phasellus eu elementum quam. Nullam at congue odio. Maecenas pellentesque ante at erat varius posuere. Nullam quis nulla varius, tempor massa nec, sodales est." }
//    p { +"Duis id purus ligula. Integer congue nulla augue, sit amet gravida ligula varius eget. Integer ut eros non justo sagittis varius. Aenean ac ex vitae turpis commodo iaculis sit amet in ipsum. Nam nec dignissim urna, at consectetur magna. Nulla facilisi. Suspendisse ut tortor vel nibh consequat cursus. Suspendisse est elit, sollicitudin vel felis nec, suscipit tincidunt quam. Suspendisse tincidunt, orci eu porttitor blandit, metus arcu commodo quam, vel porttitor risus lectus eu magna." }
//    p { +"Suspendisse suscipit vel dolor vitae lobortis. Nam nec velit nec eros facilisis molestie quis id orci. Sed sit amet ligula fringilla, porttitor felis sed, malesuada orci. Vivamus vehicula nisl nec eros pretium vulputate. Sed sit amet sem lectus. Morbi fermentum nibh sed nisl faucibus, ac interdum orci rutrum. Pellentesque vel convallis libero, in laoreet ante. Pellentesque viverra nulla eget massa blandit feugiat. Suspendisse potenti. Proin ultricies quis eros a lobortis. Aliquam erat volutpat. Curabitur vitae tortor leo." }
//    p { +"Fusce at vulputate est, vitae ultrices leo. Vivamus vestibulum molestie orci a commodo. Curabitur commodo lectus quis quam efficitur consectetur. Sed sit amet nisl fringilla, dignissim leo et, posuere eros. Pellentesque lobortis arcu velit, ut faucibus sapien placerat ac. Aliquam tincidunt ipsum nibh. Ut finibus libero vitae mauris tincidunt, at mollis mi feugiat. Duis eu orci ut mi egestas aliquet finibus vel quam. Mauris porta, urna id malesuada tincidunt, urna ante maximus nulla, at pretium dui odio non lorem." }
//    p { +"Donec sit amet orci mi. Maecenas lacinia pulvinar eros in gravida. Vivamus tempor, ante eget laoreet dapibus, libero nibh blandit sapien, non vehicula velit elit nec magna. Duis et elementum nibh, fermentum euismod urna. Morbi mattis aliquam mauris, a commodo augue hendrerit eget. Sed at ornare augue, ac iaculis orci. Vestibulum placerat est purus, et suscipit turpis facilisis ut. Sed eget metus tincidunt, commodo ligula id, aliquet dui. Pellentesque sollicitudin id erat in congue. Nunc pellentesque cursus felis, eget luctus nisl venenatis eget. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Suspendisse laoreet diam lectus, ut imperdiet mi imperdiet eget." }
//    p { +"Vestibulum congue quam a eleifend fringilla. Integer pretium tortor non vehicula egestas. Aliquam orci tortor, fermentum sed pellentesque sed, tempus sit amet ipsum. Maecenas vulputate laoreet libero eu lobortis. Mauris aliquet tortor eros, eu lacinia dui commodo quis. Etiam ut commodo lacus, vel facilisis nunc. Fusce felis ligula, egestas vel justo et, porta dignissim neque. Vestibulum a justo posuere, interdum libero eu, efficitur dui. Curabitur id pharetra ante. Donec dapibus leo id metus pulvinar, at fringilla ligula sollicitudin. Integer sit amet aliquet nulla. Proin dictum vitae massa id porttitor." }
//    p { +"Nunc dapibus justo eget risus sollicitudin, non mattis magna maximus. Nullam neque odio, cursus eget ante ac, pretium ullamcorper arcu. Suspendisse dignissim mi at nulla gravida, a fermentum magna placerat. Sed rutrum, nibh eu vulputate vehicula, justo metus vulputate sapien, eu condimentum lorem est sed nisi. In commodo sit amet sem sit amet pretium. Ut et cursus diam. Sed porttitor aliquet diam eu finibus. Nullam eu mauris congue, congue erat nec, ultricies enim. Nullam a feugiat ligula. Sed et mi luctus, fringilla nunc vitae, efficitur nunc." }
//    p { +"In rutrum magna pharetra lectus lacinia fermentum. Vestibulum eu velit enim. Aenean sapien mi, pretium id laoreet ultrices, mollis luctus massa. Cras cursus in tortor vitae mollis. Vivamus diam nisi, mollis quis turpis eu, congue rhoncus ipsum. Nunc euismod dictum velit, ac maximus velit interdum eget. Praesent leo erat, congue a pulvinar eu, euismod ac elit. Nam sed ornare lectus. Donec egestas dui sed tellus vulputate, condimentum sodales metus elementum. Nulla condimentum justo at augue laoreet, vel lacinia metus imperdiet. Nullam finibus ac nibh sit amet semper. Curabitur nunc felis, porta sit amet justo at, semper lobortis velit. Mauris est ipsum, cursus eu eros vitae, cursus pulvinar erat. Phasellus lobortis sit amet nisl in eleifend." }
//    p { +"Quisque id nisi id est maximus sollicitudin. In hac habitasse platea dictumst. Pellentesque mattis augue id laoreet scelerisque. Vivamus posuere fermentum augue eget luctus. Proin hendrerit congue orci, ac sollicitudin quam euismod quis. Donec ut turpis non tortor porta interdum vitae ac mauris. Mauris rutrum ligula leo, nec convallis nisi maximus nec. Ut viverra scelerisque odio in pulvinar. Mauris ornare eget magna ut ultrices. Sed et mollis tellus. Ut ut turpis finibus, blandit ipsum vitae, ultricies orci. Fusce luctus sem et elit varius accumsan. Donec euismod scelerisque lorem. Mauris cursus ante sed nunc auctor iaculis. Pellentesque dignissim metus at sapien varius fermentum." }
//    p { +"Vestibulum sodales, nisl nec tempor pharetra, purus nunc varius dolor, at tincidunt erat enim quis enim. Quisque rutrum, libero ut elementum pellentesque, ipsum leo porta quam, quis porttitor urna turpis eget nisl. Vestibulum in facilisis ex, at volutpat enim. Curabitur tempor cursus ullamcorper. Cras malesuada auctor sem sed viverra. Vestibulum magna eros, porta quis aliquam ut, ultricies vitae arcu. Donec commodo consectetur lectus at luctus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Pellentesque quis libero tempor, porta turpis ut, fringilla mi. Nullam sit amet tellus dolor." }
//    p { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut sodales sem quis urna dapibus lobortis. Suspendisse vitae luctus leo. Curabitur mattis dui lorem. Ut ultricies egestas magna sed sagittis. Vestibulum vulputate iaculis aliquam. Etiam odio tortor, consequat sed mollis semper, vulputate in nisi. Suspendisse massa leo, euismod a tellus nec, faucibus commodo est. Pellentesque massa libero, faucibus at faucibus sit amet, dapibus sed massa. Integer urna lectus, varius et lacus gravida, euismod pulvinar justo. Morbi a elit nibh. Phasellus eu elementum quam. Nullam at congue odio. Maecenas pellentesque ante at erat varius posuere. Nullam quis nulla varius, tempor massa nec, sodales est." }
//    p { +"Duis id purus ligula. Integer congue nulla augue, sit amet gravida ligula varius eget. Integer ut eros non justo sagittis varius. Aenean ac ex vitae turpis commodo iaculis sit amet in ipsum. Nam nec dignissim urna, at consectetur magna. Nulla facilisi. Suspendisse ut tortor vel nibh consequat cursus. Suspendisse est elit, sollicitudin vel felis nec, suscipit tincidunt quam. Suspendisse tincidunt, orci eu porttitor blandit, metus arcu commodo quam, vel porttitor risus lectus eu magna." }
//    p { +"Suspendisse suscipit vel dolor vitae lobortis. Nam nec velit nec eros facilisis molestie quis id orci. Sed sit amet ligula fringilla, porttitor felis sed, malesuada orci. Vivamus vehicula nisl nec eros pretium vulputate. Sed sit amet sem lectus. Morbi fermentum nibh sed nisl faucibus, ac interdum orci rutrum. Pellentesque vel convallis libero, in laoreet ante. Pellentesque viverra nulla eget massa blandit feugiat. Suspendisse potenti. Proin ultricies quis eros a lobortis. Aliquam erat volutpat. Curabitur vitae tortor leo." }
//    p { +"Fusce at vulputate est, vitae ultrices leo. Vivamus vestibulum molestie orci a commodo. Curabitur commodo lectus quis quam efficitur consectetur. Sed sit amet nisl fringilla, dignissim leo et, posuere eros. Pellentesque lobortis arcu velit, ut faucibus sapien placerat ac. Aliquam tincidunt ipsum nibh. Ut finibus libero vitae mauris tincidunt, at mollis mi feugiat. Duis eu orci ut mi egestas aliquet finibus vel quam. Mauris porta, urna id malesuada tincidunt, urna ante maximus nulla, at pretium dui odio non lorem." }
//    p { +"Donec sit amet orci mi. Maecenas lacinia pulvinar eros in gravida. Vivamus tempor, ante eget laoreet dapibus, libero nibh blandit sapien, non vehicula velit elit nec magna. Duis et elementum nibh, fermentum euismod urna. Morbi mattis aliquam mauris, a commodo augue hendrerit eget. Sed at ornare augue, ac iaculis orci. Vestibulum placerat est purus, et suscipit turpis facilisis ut. Sed eget metus tincidunt, commodo ligula id, aliquet dui. Pellentesque sollicitudin id erat in congue. Nunc pellentesque cursus felis, eget luctus nisl venenatis eget. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Suspendisse laoreet diam lectus, ut imperdiet mi imperdiet eget." }
//    p { +"Vestibulum congue quam a eleifend fringilla. Integer pretium tortor non vehicula egestas. Aliquam orci tortor, fermentum sed pellentesque sed, tempus sit amet ipsum. Maecenas vulputate laoreet libero eu lobortis. Mauris aliquet tortor eros, eu lacinia dui commodo quis. Etiam ut commodo lacus, vel facilisis nunc. Fusce felis ligula, egestas vel justo et, porta dignissim neque. Vestibulum a justo posuere, interdum libero eu, efficitur dui. Curabitur id pharetra ante. Donec dapibus leo id metus pulvinar, at fringilla ligula sollicitudin. Integer sit amet aliquet nulla. Proin dictum vitae massa id porttitor." }
//    p { +"Nunc dapibus justo eget risus sollicitudin, non mattis magna maximus. Nullam neque odio, cursus eget ante ac, pretium ullamcorper arcu. Suspendisse dignissim mi at nulla gravida, a fermentum magna placerat. Sed rutrum, nibh eu vulputate vehicula, justo metus vulputate sapien, eu condimentum lorem est sed nisi. In commodo sit amet sem sit amet pretium. Ut et cursus diam. Sed porttitor aliquet diam eu finibus. Nullam eu mauris congue, congue erat nec, ultricies enim. Nullam a feugiat ligula. Sed et mi luctus, fringilla nunc vitae, efficitur nunc." }
//    p { +"In rutrum magna pharetra lectus lacinia fermentum. Vestibulum eu velit enim. Aenean sapien mi, pretium id laoreet ultrices, mollis luctus massa. Cras cursus in tortor vitae mollis. Vivamus diam nisi, mollis quis turpis eu, congue rhoncus ipsum. Nunc euismod dictum velit, ac maximus velit interdum eget. Praesent leo erat, congue a pulvinar eu, euismod ac elit. Nam sed ornare lectus. Donec egestas dui sed tellus vulputate, condimentum sodales metus elementum. Nulla condimentum justo at augue laoreet, vel lacinia metus imperdiet. Nullam finibus ac nibh sit amet semper. Curabitur nunc felis, porta sit amet justo at, semper lobortis velit. Mauris est ipsum, cursus eu eros vitae, cursus pulvinar erat. Phasellus lobortis sit amet nisl in eleifend." }
//    p { +"Quisque id nisi id est maximus sollicitudin. In hac habitasse platea dictumst. Pellentesque mattis augue id laoreet scelerisque. Vivamus posuere fermentum augue eget luctus. Proin hendrerit congue orci, ac sollicitudin quam euismod quis. Donec ut turpis non tortor porta interdum vitae ac mauris. Mauris rutrum ligula leo, nec convallis nisi maximus nec. Ut viverra scelerisque odio in pulvinar. Mauris ornare eget magna ut ultrices. Sed et mollis tellus. Ut ut turpis finibus, blandit ipsum vitae, ultricies orci. Fusce luctus sem et elit varius accumsan. Donec euismod scelerisque lorem. Mauris cursus ante sed nunc auctor iaculis. Pellentesque dignissim metus at sapien varius fermentum." }
//    p { +"Vestibulum sodales, nisl nec tempor pharetra, purus nunc varius dolor, at tincidunt erat enim quis enim. Quisque rutrum, libero ut elementum pellentesque, ipsum leo porta quam, quis porttitor urna turpis eget nisl. Vestibulum in facilisis ex, at volutpat enim. Curabitur tempor cursus ullamcorper. Cras malesuada auctor sem sed viverra. Vestibulum magna eros, porta quis aliquam ut, ultricies vitae arcu. Donec commodo consectetur lectus at luctus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Pellentesque quis libero tempor, porta turpis ut, fringilla mi. Nullam sit amet tellus dolor." }

}