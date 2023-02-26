import './App.css';
import { HashRouter, Route, Routes } from 'react-router-dom';
import { CreateBlogPage } from './pages/CreateBlog';
import { BlogListPage } from './pages/BlogList';
import { ViewBlogPage } from './pages/ViewBlog'

function App() {
  return (
    <div className="App">
      <HashRouter>
        <Routes>
          <Route path='/' element={<BlogListPage />} />
          <Route path='/create-blog' element={<CreateBlogPage />} />
          <Route path='/blogs/view-blog/:id' element={<ViewBlogPage />} />
        </Routes>
      </HashRouter>
    </div>
  );
}

export default App;
