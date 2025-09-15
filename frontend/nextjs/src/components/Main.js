import Footer from "./layout/Footer"
import Header from "./layout/Header"

export default function Main ({children}) {
    return (
        <> 
            <Header/>
                {children}
            <Footer/>
        </>
    )
}