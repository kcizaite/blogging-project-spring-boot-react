import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import './ViewBlog.css'

export function ViewBlogPage() {
    const [blog, setBlog] = useState({});
    const [comment, setComment] = useState([]);
    const params = useParams();
    const [inputs, setInputs] = useState({});

    const fetchComments = () => {
        fetch(`/api/v1/comments/get-comments/${params.id}`)
            .then((responce) => responce.json())
            .then((jsonResponce) => setComment(jsonResponce))
    };

    useEffect(() => {
        fetchComments();
        fetch(`/api/v1/blogs/blog/${params.id}`)
            .then((response) => response.json())
            .then(setBlog);
    }, [params.id]);

    const createComment = () => {
        fetch(`/api/v1/comments/create-comment/${params.id}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                comment: inputs.comment,
                authorName: inputs.authorName
            })
        })
            .then(fetchComments)
    }

    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setInputs(values => ({ ...values, [name]: value }))
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log(inputs);
        console.log(params.id);
        createComment();
    }

    comment.sort((a, b) => new Date(b.createdDate) - new Date(a.createdDate));

    return (
        <div>
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
                <h3>Leave a comment</h3>
            </div>
            <div>
                <form onSubmit={handleSubmit}>
                    <div>
                        <label>Author</label>
                    </div>
                    <input
                        type="text"
                        name="authorName"
                        value={inputs.authorName || ""}
                        onChange={handleChange}
                    />
                    <label>Comment</label>
                    <input
                        type="text"
                        name="comment"
                        value={inputs.comment || ""}
                        onChange={handleChange}
                    />
                    <br></br>
                    <div>
                        <input type="submit" />
                    </div>
                    <br></br>
                    <div>
                        <h3>Comments</h3>
                    </div>
                    <div>
                        {comment.map(comment => (
                            <div key={comment.id}>
                                <h4>{comment.authorName}</h4>
                                <p className="date-text">Last edited: {comment.createdDate}</p>
                                <p>{comment.comment}</p>
                                <br></br>
                            </div>
                        ))}
                    </div>

                </form>
            </div>
        </div>
    )
}