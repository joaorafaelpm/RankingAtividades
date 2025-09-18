import Footer from "./layout/Footer";
import Header from "./layout/Header";

interface MainProps {
  // Função que atualiza a query de pesquisa (opcional)
  queryChanges?: (value: string) => void;
  // Conteúdo da página 
  children: React.ReactNode;
}

export default function Main({ queryChanges, children }: MainProps) {
  return (
    <>
      <Header onSearchChange={queryChanges || (() => {})} />
      {children}
      <Footer />
    </>
  );
}
