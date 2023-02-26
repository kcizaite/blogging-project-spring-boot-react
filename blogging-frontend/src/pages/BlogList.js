import { useEffect, useState } from "react"
import { Link, useParams } from 'react-router-dom'
import './BlogList.css'

export function BlogListPage(props) {
    const [blogs, setBlogs] = useState([])
    const params = useParams();

    const fetchBlogs = () => {
        fetch("/api/v1/blogs")
            .then((responce) => responce.json())
            .then((jsonResponce) => setBlogs(jsonResponce))
    };

    useEffect(() => {
        fetchBlogs();
    }, []);

    blogs.sort((a, b) => new Date(b.createdDate) - new Date(a.createdDate));

    return (
        <div>
            <Link to={'/create-blog'}>
                Create New
            </Link>
            <div>
                <div>
                    {blogs.map(blog => (
                        <div key={blog.id}>
                            <div>
                                <h1>{blog.headline}</h1>
                            </div>
                            <div>
                                <p className="date-text">Last edited: {blog.createdDate}</p>
                            </div>
                            <div>
                                <p>{blog.text}</p>
                            </div>
                            <br></br>
                            <div>
                                <Link to={'/blogs/view-blog/' + blog.id}>Read more</Link>
                            </div>
                            <br></br>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    )
}